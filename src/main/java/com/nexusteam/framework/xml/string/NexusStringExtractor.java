package com.nexusteam.framework.xml.string;

import com.nexusteam.framework.xml.string.cli.CommandLineOptions;
import com.nexusteam.framework.xml.string.cli.ExitCodes;
import com.nexusteam.framework.xml.string.core.ExtractionEngine;
import com.nexusteam.framework.xml.string.exception.ExtractionException;
import com.nexusteam.framework.xml.string.model.ExtractionConfig;
import com.nexusteam.framework.xml.string.model.TransformationResult;
import com.nexusteam.framework.xml.string.util.Logger;
import picocli.CommandLine;

import java.nio.file.Files;
import java.nio.file.Path;

/**
 * CLI entry point — NexusStringExtractor 0.1.0
 *
 * <pre>
 * java -jar nexus-string-extractor-0.1.0.jar -i &lt;res-dir&gt; [options]
 * </pre>
 */
public final class NexusStringExtractor {

    public static void main(String[] args) {
        CommandLineOptions options = new CommandLineOptions();
        CommandLine cmd = new CommandLine(options);
        try {
            cmd.parseArgs(args);
            if (cmd.isUsageHelpRequested()) {
                cmd.usage(System.out);
                System.exit(ExitCodes.SUCCESS);
            }
            if (cmd.isVersionHelpRequested()) {
                cmd.printVersionHelp(System.out);
                System.exit(ExitCodes.SUCCESS);
            }

            Path resDir = options.getResDir();
            if (resDir == null || !Files.isDirectory(resDir)) {
                System.err.println("Error: -i must be an existing res/ directory.");
                cmd.usage(System.err);
                System.exit(ExitCodes.BAD_ARGS);
            }

            Path stringsXml = options.getStringsXml() != null
                    ? options.getStringsXml()
                    : resDir.resolve("values").resolve("strings.xml");

            ExtractionConfig config = ExtractionConfig.builder()
                    .resDir(resDir.toAbsolutePath().normalize())
                    .stringsXml(stringsXml.toAbsolutePath().normalize())
                    .dryRun(options.isDryRun())
                    .backup(options.isBackup())
                    .verbose(options.isVerbose())
                    .prefix(options.getPrefix())
                    .smartNames(options.isSmartNames())
                    .build();

            TransformationResult result = new ExtractionEngine().run(config);
            if (result.getStringsExtracted() == 0) {
                Logger.info("No hardcoded strings found.");
            }
            System.exit(ExitCodes.SUCCESS);

        } catch (CommandLine.ParameterException e) {
            System.err.println("Error: " + e.getMessage());
            cmd.usage(System.err);
            System.exit(ExitCodes.BAD_ARGS);
        } catch (ExtractionException e) {
            Logger.severe("Extraction failed: " + e.getMessage(), e);
            System.exit(ExitCodes.ERROR);
        } catch (Exception e) {
            Logger.severe("Unexpected error: " + e.getMessage(), e);
            System.exit(ExitCodes.ERROR);
        }
    }

    private NexusStringExtractor() {
    }
}
