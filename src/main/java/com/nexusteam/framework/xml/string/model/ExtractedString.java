package com.nexusteam.framework.xml.string.model;

import java.util.Objects;

/** Immutable representation of a hardcoded string extracted from a layout attribute. */
public final class ExtractedString {

    private final String value;
    private final String attributeName;
    private final String generatedName;
    private final String layoutPath;

    public ExtractedString(String value, String attributeName, String generatedName, String layoutPath) {
        this.value = Objects.requireNonNull(value, "value");
        this.attributeName = Objects.requireNonNull(attributeName, "attributeName");
        this.generatedName = Objects.requireNonNull(generatedName, "generatedName");
        this.layoutPath = Objects.requireNonNull(layoutPath, "layoutPath");
    }

    public String getValue() { return value; }
    public String getAttributeName() { return attributeName; }
    public String getGeneratedName() { return generatedName; }
    public String getLayoutPath() { return layoutPath; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExtractedString)) return false;
        ExtractedString that = (ExtractedString) o;
        return value.equals(that.value)
                && attributeName.equals(that.attributeName)
                && generatedName.equals(that.generatedName)
                && layoutPath.equals(that.layoutPath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, attributeName, generatedName, layoutPath);
    }

    @Override
    public String toString() {
        return "ExtractedString{name='" + generatedName + "', value='" + value
                + "', attr='" + attributeName + "', layout='" + layoutPath + "'}";
    }
}
