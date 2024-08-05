package com.cleardragonf.hobpayments.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class ModConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final CommonConfig COMMON;

    static {
        final Pair<CommonConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class CommonConfig {
        public final ForgeConfigSpec.DoubleValue defaultBalance;

        public CommonConfig(ForgeConfigSpec.Builder builder) {
            builder.comment("Economy settings")
                    .push("economy");

            defaultBalance = builder
                    .comment("Default balance for new players")
                    .defineInRange("defaultBalance", 0.0, 0.0, Double.MAX_VALUE);

            builder.pop();
        }
    }
}
