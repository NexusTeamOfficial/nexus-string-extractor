package com.nexusteam.framework.xml.string.cli;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.nio.file.Path;
import java.util.concurrent.Callable;

@Command(
        name = "nexus-string-extractor",
        mixinStandardHelpOptions = true,
        version = "NexusStringExtractor 0.1.0",
        description = "Extracts hardcoded string literals from Android layout XML into strings.xml "
                + "with smart readable names, and replaces them with @string/ references.",
        exitCodeOnInvalidInput = ExitCodes.BAD_ARGS,
        exitCodeOnExecutionException = ExitCodes.ERROR
)
public class CommandLineOptions implements Callable<Integer> {

    @Option(names = {"-i", "--input"}, required = true,
            description = "Path to the Android res/ directory.")
    private Path resDir;

    @Option(names = {"-o", "--output"},
            description = "Target strings.xml (default: <res>/values/strings.xml).")
    private Path stringsXml;

    @Option(names = {"--dry-run"}, description = "Analyse only; write nothing.")
    private boolean dryRun;

    @Option(names = {"--backup"}, description = "Create .bak copies before overwrite.")
    private boolean backup;

    @Option(names = {"--verbose", "-v"}, description = "Verbose logging.")
    private boolean verbose;

    @Option(names = {"--prefix"}, description = "Prefix for every generated name (e.g. str_).")
    private String prefix;

    @Option(names = {"--no-smart-names"},
            description = "Disable smart names; use sequential a, b, c… only.")
    private boolean noSmartNames;

    public Path getResDir() { return resDir; }
    public Path getStringsXml() { return stringsXml; }
    public boolean isDryRun() { return dryRun; }
    public boolean isBackup() { return backup; }
    public boolean isVerbose() { return verbose; }
    public String getPrefix() { return prefix; }
    public boolean isSmartNames() { return !noSmartNames; }

    @Override
    public Integer call() {
        return ExitCodes.SUCCESS;
    }
}
