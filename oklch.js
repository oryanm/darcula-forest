/**
 * Bidirectional conversion between hex and oklch color values.
 *
 * Usage (CLI):
 *   node oklch.js "#5a792c" "#8aac5f"
 *   node oklch.js 5a792c 8aac5f
 *
 * Usage (module):
 *   const { hexToOklch, oklchToHex } = require('./oklch');
 */

function hexToOklch(hex) {
  hex = hex.replace(/^#/, "");
  const r = parseInt(hex.slice(0, 2), 16) / 255;
  const g = parseInt(hex.slice(2, 4), 16) / 255;
  const b = parseInt(hex.slice(4, 6), 16) / 255;

  // sRGB → linear RGB
  const toLinear = (c) =>
    c <= 0.04045 ? c / 12.92 : Math.pow((c + 0.055) / 1.055, 2.4);
  const lr = toLinear(r),
    lg = toLinear(g),
    lb = toLinear(b);

  // linear RGB → XYZ (D65)
  const x = 0.4124564 * lr + 0.3575761 * lg + 0.1804375 * lb;
  const y = 0.2126729 * lr + 0.7151522 * lg + 0.072175 * lb;
  const z = 0.0193339 * lr + 0.119192 * lg + 0.9503041 * lb;

  // XYZ → LMS
  const l = 0.8189330101 * x + 0.3618667424 * y - 0.1288597137 * z;
  const m = 0.0329845436 * x + 0.9293118715 * y + 0.0361456387 * z;
  const s = 0.0482003018 * x + 0.2643662691 * y + 0.633851707 * z;

  // LMS → LMS (cube root)
  const l_ = Math.cbrt(l),
    m_ = Math.cbrt(m),
    s_ = Math.cbrt(s);

  // LMS → Oklab
  const L = 0.2104542553 * l_ + 0.793617785 * m_ - 0.0040720468 * s_;
  const A = 1.9779984951 * l_ - 2.428592205 * m_ + 0.4505937099 * s_;
  const B = 0.0259040371 * l_ + 0.7827717662 * m_ - 0.808675766 * s_;

  // Oklab → Oklch
  const C = Math.sqrt(A * A + B * B);
  let H = (Math.atan2(B, A) * 180) / Math.PI;
  if (H < 0) H += 360;

  return `oklch(${(L * 100).toFixed(1)}% ${C.toFixed(3)} ${H.toFixed(1)})`;
}

function oklchToHex(oklch) {
  const match = oklch.match(/oklch\(\s*([\d.]+)(%?)\s+([\d.]+)\s+([\d.]+)\s*\)/);
  if (!match) return null;
  const rawL = parseFloat(match[1]);
  const hasPercent = match[2] === "%";
  const L = hasPercent ? rawL / 100 : rawL;
  const C = parseFloat(match[3]);
  const H = (parseFloat(match[4]) * Math.PI) / 180;

  // Oklch → Oklab
  const a = C * Math.cos(H);
  const b = C * Math.sin(H);

  // Oklab → LMS (cube root)
  const l_ = L + 0.3963377774 * a + 0.2158037573 * b;
  const m_ = L - 0.1055613458 * a - 0.0638541728 * b;
  const s_ = L - 0.0894841775 * a - 1.291485548 * b;

  // LMS (cube root) → LMS
  const l = l_ * l_ * l_;
  const m = m_ * m_ * m_;
  const s = s_ * s_ * s_;

  // LMS → linear RGB
  const lr = 4.0767416621 * l - 3.3077115913 * m + 0.2309699292 * s;
  const lg = -1.2684380046 * l + 2.6097574011 * m - 0.3413193965 * s;
  const lb = -0.0041960863 * l - 0.7034186147 * m + 1.707614701 * s;

  // linear RGB → sRGB
  const toSrgb = (c) => {
    c = Math.max(0, Math.min(1, c));
    return c <= 0.0031308 ? 12.92 * c : 1.055 * Math.pow(c, 1 / 2.4) - 0.055;
  };

  const toHexByte = (c) =>
    Math.round(toSrgb(c) * 255)
      .toString(16)
      .padStart(2, "0");
  return toHexByte(lr) + toHexByte(lg) + toHexByte(lb);
}

// ── Exports ──────────────────────────────────────────────────────────
if (typeof module !== "undefined") {
  module.exports = { hexToOklch, oklchToHex };

  // CLI
  if (require.main === module) {
    const args = process.argv.slice(2);
    if (args.length === 0) {
      console.log("Usage: node oklch.js <hex> [hex ...]");
      process.exit(1);
    }
    for (const arg of args) {
      const hex = arg.startsWith("#") ? arg : `#${arg}`;
      console.log(`${hex} → ${hexToOklch(hex)}`);
    }
  }
}
