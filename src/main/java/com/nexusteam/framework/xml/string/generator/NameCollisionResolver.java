package com.nexusteam.framework.xml.string.generator;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Allocates unique resource names.
 * Smart mode: derive from value, then name, name_2, name_3…
 * Fallback: sequential a, b, … via NameGenerator
 */
public final class NameCollisionResolver {

    private final Set<String> existingNames;
    private final NameGenerator sequential;
    private final String prefix;
    private final boolean smartNames;

    public NameCollisionResolver(Set<String> existingNames, NameGenerator sequential,
                                 String prefix, boolean smartNames) {
        this.existingNames = new HashSet<>(Objects.requireNonNull(existingNames));
        this.sequential = Objects.requireNonNull(sequential);
        this.prefix = (prefix == null || prefix.isEmpty()) ? "" : prefix;
        this.smartNames = smartNames;
    }

    public String nextUnique(String value) {
        if (smartNames) {
            String base = SmartNameGenerator.fromValue(value);
            if (base != null) {
                String candidate = prefix + base;
                if (!existingNames.contains(candidate)) {
                    existingNames.add(candidate);
                    return candidate;
                }
                int i = 2;
                while (true) {
                    String numbered = candidate + "_" + i;
                    if (!existingNames.contains(numbered)) {
                        existingNames.add(numbered);
                        return numbered;
                    }
                    i++;
                }
            }
        }
        String name;
        do {
            name = sequential.next();
        } while (existingNames.contains(name));
        existingNames.add(name);
        return name;
    }

    public Set<String> getKnownNames() {
        return Set.copyOf(existingNames);
    }
}
