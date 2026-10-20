# NexusStringExtractor

[![Version](https://img.shields.io/badge/version-0.1.0-blue.svg)](CHANGELOG.md)
[![JitPack](https://jitpack.io/v/NexusTeamOfficial/nexus-string-extractor.svg)](https://jitpack.io/#NexusTeamOfficial/nexus-string-extractor)
[![Java](https://img.shields.io/badge/Java-11%2B-orange.svg)](#)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](LICENSE)

**Build-time** tool that scans Android layout XML, extracts hardcoded string literals into `strings.xml` with **readable smart names**, and replaces them with `@string/` references.

```text
android:text="Welcome to Nexus"  →  android:text="@string/welcome_to_nexus"
```

---

## Gradle — `implementation` se download

JitPack se seedha library add karo (koi Maven Central publish zaroori nahi).

### 1) Root `settings.gradle` (ya `settings.gradle.kts`)

```gradle
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }   // ← add this
    }
}
```

Agar purana project hai to **root** `build.gradle` me:

```gradle
allprojects {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

### 2) App / module `build.gradle`

```gradle
dependencies {
    // Library API (StringExtractor.builder()…)
    implementation 'com.github.NexusTeamOfficial:nexus-string-extractor:0.1.0'
}
```

Tag ke baad version:

| Version | Dependency |
|---------|------------|
| Latest commit | `com.github.NexusTeamOfficial:nexus-string-extractor:main-SNAPSHOT` |
| Tag `0.1.0` / `v0.1.0` | `com.github.NexusTeamOfficial:nexus-string-extractor:0.1.0` |

> Pehli baar JitPack build 1–2 minute le sakta hai. Status: [jitpack.io/#NexusTeamOfficial/nexus-string-extractor](https://jitpack.io/#NexusTeamOfficial/nexus-string-extractor)

### 3) Code me use

```java
import com.nexusteam.framework.xml.string.StringExtractor;
import com.nexusteam.framework.xml.string.model.TransformationResult;

TransformationResult r = StringExtractor.builder()
    .resDir("app/src/main/res")   // path to res/
    .smartNames(true)
    .backup(true)
    .verbose(true)
    .prefix("str_")              // optional
    .build()
    .extract();

System.out.println("Extracted: " + r.getStringsExtracted());
```

**Note:** Ye **build-time** tool hai (Gradle task / desktop / utility app). Normal Android Activity me runtime UI ke liye mat chalao.

### CLI JAR (manual download)

```bash
git clone https://github.com/NexusTeamOfficial/nexus-string-extractor.git
cd nexus-string-extractor
./gradlew shadowJar
# → build/libs/nexus-string-extractor-0.1.0.jar

java -jar build/libs/nexus-string-extractor-0.1.0.jar -i app/src/main/res --backup
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
| **Library + CLI** | `implementation` via JitPack or `java -jar` |

---

## CLI options

| Option | Description |
|--------|-------------|
| `-i`, `--input` | **Required.** Path to `res/` |
| `-o`, `--output` | Target `strings.xml` (default: `res/values/strings.xml`) |
| `--dry-run` | Report only |
| `--backup` | Write `.bak` before overwrite |
| `-v`, `--verbose` | Fine logs |
| `--prefix` | Name prefix |
| `--no-smart-names` | Force sequential `a`, `b`, `c`… |

**Exit codes:** `0` success · `1` error · `2` bad args

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

## Tag release (JitPack ke liye)

GitHub pe **Releases → Create a new release**:

1. Tag: `0.1.0` (ya `v0.1.0`)
2. Title: `v0.1.0`
3. Publish

Uske baad:

```gradle
implementation 'com.github.NexusTeamOfficial:nexus-string-extractor:0.1.0'
```

---

## Requirements

- JDK 11+
- Gradle 8.x

## License

MIT — see [LICENSE](LICENSE).
