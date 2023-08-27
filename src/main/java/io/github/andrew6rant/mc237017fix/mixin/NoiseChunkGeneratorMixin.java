package io.github.andrew6rant.mc237017fix.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.structure.StructureSet;
import net.minecraft.util.math.noise.DoublePerlinNoiseSampler;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.source.BiomeSource;
import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NoiseChunkGenerator.class})
public class NoiseChunkGeneratorMixin {

	@Shadow @Final protected RegistryEntry<ChunkGeneratorSettings> settings;

	@ModifyVariable(method = "<init>",
	at = @At(value = "STORE"))
	private AquiferSampler.FluidLevel mc237017fix$modifyLavaFluidLevelSampler(AquiferSampler.FluidLevel fluidLevel) {
		ChunkGeneratorSettings chunkGeneratorSettings = this.settings.value();
		return new AquiferSampler.FluidLevel(chunkGeneratorSettings.seaLevel(), chunkGeneratorSettings.defaultFluid());
	}
}