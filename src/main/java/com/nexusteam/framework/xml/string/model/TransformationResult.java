package com.nexusteam.framework.xml.string.model;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/** Immutable summary of a completed extraction run. */
public final class TransformationResult {

    private final int layoutsScanned;
    private final int layoutsModified;
    private final int layoutsSkipped;
    private final int stringsExtracted;
    private final List<ExtractedString> allExtracted;
    private final boolean dryRun;

    public TransformationResult(int layoutsScanned,
                                int layoutsModified,
                                int layoutsSkipped,
                                int stringsExtracted,
                                List<ExtractedString> allExtracted,
                                boolean dryRun) {
        this.layoutsScanned = layoutsScanned;
        this.layoutsModified = layoutsModified;
        this.layoutsSkipped = layoutsSkipped;
        this.stringsExtracted = stringsExtracted;
        this.allExtracted = Collections.unmodifiableList(Objects.requireNonNull(allExtracted));
        this.dryRun = dryRun;
    }

    public int getLayoutsScanned() { return layoutsScanned; }
    public int getLayoutsModified() { return layoutsModified; }
    public int getLayoutsSkipped() { return layoutsSkipped; }
    public int getStringsExtracted() { return stringsExtracted; }
    public List<ExtractedString> getAllExtracted() { return allExtracted; }
    public boolean isDryRun() { return dryRun; }

    @Override
    public String toString() {
        return "TransformationResult{scanned=" + layoutsScanned
                + ", modified=" + layoutsModified
                + ", skipped=" + layoutsSkipped
                + ", extracted=" + stringsExtracted
                + ", dryRun=" + dryRun + "}";
    }
}
