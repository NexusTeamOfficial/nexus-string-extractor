# NexusStringExtractor

[![Version](https://img.shields.io/badge/version-0.1.0-blue.svg)](CHANGELOG.md)
[![Java](https://img.shields.io/badge/Java-11%2B-orange.svg)](#)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

**Build-time** tool that scans Android layout XML, extracts hardcoded string literals into `strings.xml` with **readable smart names**, and replaces them with `@string/` references.

```text
android:text="Welcome to Nexus"  →  android:text="@string/welcome_to_nexus"
```

---

## Features (0.1.0)

| Feature | Description |
|---------|-------------|
| **Smart names** | `"Enter your name"` → `enter_your_name` |
| **Sequential fallback** | `a`, `b`, … `z`, `aa` when smart name is empty |
| **Collision safe** | `login`, `login_2`, `login_3`… |
| **Prefix** | `--prefix str_` → `str_welcome_to_nexus` |
| **Attributes** | `text`, `hint`, `contentDescription`, `tooltipText` |
| **Skip refs** | Leaves `@string/…` and `?attr/…` alone |
| **Dry-run / backup** | Safe trial runs + `.bak` files |
| **Resilient parse** | Bad layouts are skipped, not fatal |
| **Android-safe XML** | No crash on missing Apache parser features |
| **Library + CLI** | Use as JAR API or `java -jar` |

---

## Quick start

### Build

```bash
./gradlew shadowJar
# → build/libs/nexus-string-extractor-0.1.0.jar
```

### CLI

```bash
java -jar build/libs/nexus-string-extractor-0.1.0.jar \
  -i app/src/main/res \
  --backup --verbose
```

| Option | Description |
|--------|-------------|
| `-i`, `--input` | **Required.** Path to `res/` |
| `-o`, `--output` | Target `strings.xml` (default: `res/values/strings.xml`) |
| `--dry-run` | Report only |
| `--backup` | Write `.bak` before overwrite |
| `-v`, `--verbose` | Fine logs |
| `--prefix` | Name prefix |
| `--no-smart-names` | Force sequential `a`, `b`, `c`… |
| `-h`, `--help` | Help |
| `-V`, `--version` | Version |

**Exit codes:** `0` success · `1` error · `2` bad args

### Library API

```java
import com.nexusteam.framework.xml.string.StringExtractor;
import com.nexusteam.framework.xml.string.model.TransformationResult;

TransformationResult r = StringExtractor.builder()
    .resDir("app/src/main/res")
    .smartNames(true)
    .backup(true)
    .verbose(true)
    .prefix("str_")
    .build()
    .extract();

System.out.println(r.getStringsExtracted());
```

---

## Before / after

**Layout (before)**

```xml
<TextView android:text="Welcome to Nexus" />
<EditText android:hint="Enter your name" />
```

**Layout (after)**

```xml
<TextView android:text="@string/welcome_to_nexus" />
<EditText android:hint="@string/enter_your_name" />
```

**strings.xml**

```xml
<string name="welcome_to_nexus">Welcome to Nexus</string>
<string name="enter_your_name">Enter your name</string>
```

---

## Project layout

```text
nexus-string-extractor/
├── build.gradle
├── settings.gradle
├── README.md
├── CHANGELOG.md
├── LICENSE
├── .github/workflows/ci.yml
└── src/main/java/com/nexusteam/framework/xml/string/
    ├── NexusStringExtractor.java
    ├── StringExtractor.java
    ├── cli/
    ├── core/
    ├── parser/
    ├── generator/
    ├── writer/
    ├── model/
    ├── util/
    └── exception/
```

---

## Requirements

- JDK 11+
- Gradle 8.x (wrapper recommended)

---

## License

MIT — see [LICENSE](LICENSE).
