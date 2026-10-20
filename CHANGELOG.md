# Changelog

All notable changes to this project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.1.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [0.1.0] — 2026-10-20

### Added
- Initial public release
- Scan `res/layout/**/*.xml` for hardcoded strings
- Attributes: `android:text`, `hint`, `contentDescription`, `tooltipText`
- **Smart names** from content (`Welcome to App` → `welcome_to_app`)
- Sequential fallback names (`a`, `b`, …, `z`, `aa`, …)
- Collision resolution (`name`, `name_2`, `name_3`, …)
- Optional `--prefix`
- `--dry-run`, `--backup`, `--verbose`
- Skip unreadable layouts instead of aborting the whole run
- Android-safe XML feature detection (no hard fail on missing Apache features)
- Library API: `StringExtractor.builder()…extract()`
- CLI fat JAR via Shadow plugin
- JUnit 5 tests for naming, escaping, attribute filter

### Notes
- Build-time tool (not a runtime Android dependency for on-device UI)
- Idempotent: already `@string/` / `?attr/` values are left alone
