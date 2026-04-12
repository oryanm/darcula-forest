/**
 * Generates Darcula_Forest.icls from the color palette.
 *
 * Usage:  node generate-icls.js
 * Output: writes Darcula_Forest.icls in the same directory
 */

const { readFileSync, writeFileSync } = require("fs");
const { join } = require("path");
const { oklchToHex } = require("../oklch");

// ── Parse palette.css ────────────────────────────────────────────────
// Reads CSS custom properties from palette.css. Supports both hex and
// oklch() values — oklch is converted to hex for the icls file.

function parsePaletteCss(path) {
  const css = readFileSync(path, "utf-8");
  const vars = {};
  for (const match of css.matchAll(/--([a-z][\w-]*):\s*(.+?)\s*;/g)) {
    const [, name, value] = match;
    vars[name] = value;
  }
  return vars;
}

function resolveToHex(value) {
  // #hex → strip the #
  if (value.startsWith("#")) return value.slice(1);
  // oklch(from ...) — relative color syntax, not convertible in Node
  if (value.includes("from ") || value.includes("var(")) return null;
  // oklch(...) → convert
  if (value.startsWith("oklch(")) return oklchToHex(value);
  return value;
}

const cssPath = join(__dirname, "palette.css");
const cssVars = parsePaletteCss(cssPath);

// Build palette: variable name → 6-digit hex (no #)
const palette = {};
for (const [name, value] of Object.entries(cssVars)) {
  palette[name] = resolveToHex(value);
}

// ── ICLS schema ──────────────────────────────────────────────────────
// Describes the structure of the .icls file. Palette keys are resolved
// at generation time; literal hex values are used as-is for colors that
// don't belong in the palette (inherited/static Darcula values).

const scheme = {
  name: "Darcula Forest",
  version: "142",
  parent: "Darcula",
  meta: {
    ide: "idea",
    ideVersion: "2025.3.2.0.0",
    originalScheme: "Darcula Forest",
  },

  // <colors> section — simple name→value pairs
  colors: [
    { name: "CARET_ROW_COLOR",          ref: "caret-row" },
    { name: "FOLDED_TEXT_BORDER_COLOR",  hex: "313335" },
    { name: "SELECTION_BACKGROUND",      ref: "selection-bg" },
    { name: "TEARLINE_COLOR",            hex: "3c3f41" },
  ],

  // <attributes> section — each entry can have FOREGROUND, BACKGROUND,
  // EFFECT_COLOR (as ref or hex), plus FONT_TYPE and EFFECT_TYPE.
  attributes: [
    {
      name: "ANNOTATION_ATTRIBUTE_NAME_ATTRIBUTES",
      fg: "named-arg",
    },
    {
      name: "ANNOTATION_NAME_ATTRIBUTES",
      fg: "string",
      effectType: 1,
    },
    {
      name: "DEFAULT_COMMA",
      fg: "keyword",
    },
    {
      name: "DEFAULT_CONSTANT",
      fg: "constant-field",
      fontType: 2,
    },
    {
      name: "DEFAULT_DOC_COMMENT",
      fg: "javadoc",
      fontType: 2,
    },
    {
      name: "DEFAULT_DOC_COMMENT_TAG",
      fg: "javadoc",
      fontType: 3,
      effectColor: "javadoc",
      effectType: 1
    },
    {
      name: "DEFAULT_DOC_COMMENT_TAG_VALUE",
      fg: "string",
    },
    {
      name: "DEFAULT_DOC_MARKUP",
      fg: "function-decl",
    },
    {
      name: "DEFAULT_FUNCTION_DECLARATION",
      fg: "function-decl",
    },
    {
      name: "DEFAULT_IDENTIFIER",
      fg: "fg",
      effectType: 5,
    },
    {
      name: "DEFAULT_INSTANCE_FIELD",
      fg: "constant-field",
    },
    {
      name: "DEFAULT_INVALID_STRING_ESCAPE",
      fg: "string-escape-bad",
      effectColor: "ff0000",
      effectType: 2,
    },
    {
      name: "DEFAULT_KEYWORD",
      fg: "keyword",
    },
    {
      name: "DEFAULT_NUMBER",
      fg: "number",
    },
    {
      name: "DEFAULT_SEMICOLON",
      fg: "keyword",
    },
    {
      name: "DEFAULT_STATIC_FIELD",
      fg: "constant-field",
      fontType: 2,
    },
    {
      name: "DEFAULT_STATIC_METHOD",
      fg: "static-method",
      fontType: 2,
    },
    {
      name: "DEFAULT_STRING",
      fg: "string",
    },
    {
      name: "DEFAULT_TEMPLATE_LANGUAGE_COLOR",
      bg: "3c3f41",
    },
    {
      name: "DEFAULT_VALID_STRING_ESCAPE",
      fg: "string-escape",
    },
    {
      name: "DEPRECATED_ATTRIBUTES",
      effectColor: "fg",
      effectType: 3
    },
    {
      name: "DIFF_CONFLICT",
      bg: "diff-conflict",
      errorStripeColor: "diff-conflict"
    },
    {
      name: "DIFF_DELETED",
      bg: "diff-delete",
      errorStripeColor: "diff-delete"
    },
    {
      name: "DIFF_INSERTED",
      bg: "diff-add",
      errorStripeColor: "diff-add"
    },
    {
      name: "DIFF_MODIFIED",
      bg: "diff-change",
      errorStripeColor: "diff-change"
    },
    {
      name: "IMPLICIT_ANONYMOUS_CLASS_PARAMETER_ATTRIBUTES",
      fg: "implicit-param",
      effectColor: "function-decl",
      effectType: 1,
    },
    {
      name: "INJECTED_LANGUAGE_FRAGMENT",
      bg: "282b27",
    },
    {
      name: "KOTLIN_LABEL",
      fg: "number",
    },
    {
      name: "KOTLIN_MUTABLE_VARIABLE",
      effectColor: "mutable-underline",
      effectType: 1,
    },
    {
      name: "KOTLIN_NAMED_ARGUMENT",
      fg: "named-arg",
    },
    {
      name: "SEARCH_RESULT_ATTRIBUTES",
      bg: "search-result-bg",
      errorStripeColor: "530d",
    },
    {
      name: "TEXT",
      fg: "fg",
      bg: "editor-bg",
      effectType: 5,
    },
    {
      name: "TODO_DEFAULT_ATTRIBUTES",
      fg: "todo",
      fontType: 2,
      errorStripeColor: "977ab",
    },
    {
      name: "TYPO",
      effectColor: "typo-underline",
      effectType: 2,
    },
    {
      name: "WARNING_ATTRIBUTES",
      bg: "warning",
      errorStripeColor: "be9117",
      effectType: 2,
    },
    {
      name: "WRONG_REFERENCES_ATTRIBUTES",
      fg: "error",
    },
  ],
};

// ── Resolve a value: palette key → hex, or pass through literal hex ──

function resolve(value) {
  if (palette[value] !== undefined) return palette[value];
  // Treat as literal hex if not found in palette
  return value;
}

// ── XML generation ───────────────────────────────────────────────────

function opt(name, value, indent) {
  return `${indent}<option name="${name}" value="${value}" />`;
}

function generateIcls() {
  const lines = [];
  const s = scheme;

  lines.push(`<scheme name="${s.name}" version="${s.version}" parent_scheme="${s.parent}">`);

  // metaInfo
  lines.push("  <metaInfo>");
  for (const [key, val] of Object.entries(s.meta)) {
    lines.push(`    <property name="${key}">${val}</property>`);
  }
  lines.push("  </metaInfo>");

  // colors
  lines.push("  <colors>");
  for (const c of s.colors) {
    const hex = c.ref ? resolve(c.ref) : c.hex;
    lines.push(opt(c.name, hex.toUpperCase(), "    "));
  }
  lines.push("  </colors>");

  // attributes
  lines.push("  <attributes>");
  for (const attr of s.attributes) {
    lines.push(`    <option name="${attr.name}">`);
    lines.push("      <value>");

    if (attr.fg)              lines.push(opt("FOREGROUND",        resolve(attr.fg), "        "));
    if (attr.bg)              lines.push(opt("BACKGROUND",        resolve(attr.bg), "        "));
    if (attr.fontType != null) lines.push(opt("FONT_TYPE",        String(attr.fontType), "        "));
    if (attr.errorStripeColor) lines.push(opt("ERROR_STRIPE_COLOR", resolve(attr.errorStripeColor), "        "));
    if (attr.effectColor)     lines.push(opt("EFFECT_COLOR",      resolve(attr.effectColor), "        "));
    if (attr.effectType != null) lines.push(opt("EFFECT_TYPE",    String(attr.effectType), "        "));

    lines.push("      </value>");
    lines.push("    </option>");
  }
  lines.push("  </attributes>");
  lines.push("</scheme>");

  return lines.join("\n");
}

// ── Write ────────────────────────────────────────────────────────────

const out = join(__dirname, "Darcula_Forest.icls");
writeFileSync(out, generateIcls() + "\n");
console.log(`Wrote ${out}`);
