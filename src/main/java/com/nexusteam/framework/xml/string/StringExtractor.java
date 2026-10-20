package com.nexusteam.framework.xml.string;

import com.nexusteam.framework.xml.string.core.ExtractionEngine;
import com.nexusteam.framework.xml.string.exception.ExtractionException;
import com.nexusteam.framework.xml.string.model.ExtractionConfig;
import com.nexusteam.framework.xml.string.model.TransformationResult;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Public library API (v0.1.0).
 *
 * <pre>{@code
 * TransformationResult r = StringExtractor.builder()
 *     .resDir("app/src/main/res")
 *     .smartNames(true)
 *     .backup(true)
 *     .build()
 *     .extract();
 * }</pre>
 */
public final class StringExtractor {

    private final ExtractionConfig config;

    private StringExtractor(ExtractionConfig config) {
        this.config = config;
    }

    public TransformationResult extract() throws ExtractionException {
        return new ExtractionEngine().run(config);
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private Path resDir;
        private Path stringsXml;
        private boolean dryRun;
        private boolean backup;
        private boolean verbose;
        private String prefix;
        private boolean smartNames = true;

        public Builder resDir(Path v) { this.resDir = v; return this; }
        public Builder resDir(File v) { this.resDir = v != null ? v.toPath() : null; return this; }
        public Builder resDir(String v) { this.resDir = v != null ? Paths.get(v) : null; return this; }
        public Builder stringsXml(Path v) { this.stringsXml = v; return this; }
        public Builder stringsXml(File v) { this.stringsXml = v != null ? v.toPath() : null; return this; }
        public Builder stringsXml(String v) { this.stringsXml = v != null ? Paths.get(v) : null; return this; }
        public Builder dryRun(boolean v) { this.dryRun = v; return this; }
        public Builder backup(boolean v) { this.backup = v; return this; }
        public Builder verbose(boolean v) { this.verbose = v; return this; }
        public Builder prefix(String v) { this.prefix = v; return this; }
        public Builder smartNames(boolean v) { this.smartNames = v; return this; }

        public StringExtractor build() {
            Objects.requireNonNull(resDir, "resDir is required");
            Path resolved = stringsXml != null
                    ? stringsXml
                    : resDir.resolve("values").resolve("strings.xml");
            ExtractionConfig cfg = ExtractionConfig.builder()
                    .resDir(resDir.toAbsolutePath().normalize())
                    .stringsXml(resolved.toAbsolutePath().normalize())
                    .dryRun(dryRun)
                    .backup(backup)
                    .verbose(verbose)
                    .prefix(prefix)
                    .smartNames(smartNames)
                    .build();
            return new StringExtractor(cfg);
        }
    }
}
