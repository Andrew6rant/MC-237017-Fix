package io.github.andrew6rant.mc237017fix.mixin;

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
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({NoiseChunkGenerator.class})
public class NoiseChunkGeneratorMixin {
	@Mutable @Shadow @Final private AquiferSampler.FluidLevelSampler fluidLevelSampler;

	@Shadow @Final protected RegistryEntry<ChunkGeneratorSettings> settings;

	@Inject(method = "<init>(Lnet/minecraft/util/registry/Registry;Lnet/minecraft/util/registry/Registry;Lnet/minecraft/world/biome/source/BiomeSource;Lnet/minecraft/util/registry/RegistryEntry;)V", at = @At(value = "TAIL"))
	private void mc237017fix$redirectFluidLevelSampler(Registry<StructureSet> structureSetRegistry, Registry<DoublePerlinNoiseSampler.NoiseParameters> noiseRegistry, BiomeSource populationSource, RegistryEntry<ChunkGeneratorSettings> chunkGeneratorSettingsRegistryEntry, CallbackInfo ci) {
		ChunkGeneratorSettings chunkGeneratorSettings = this.settings.value();
		this.fluidLevelSampler = (x, y, z) -> new AquiferSampler.FluidLevel(chunkGeneratorSettings.seaLevel(), chunkGeneratorSettings.defaultFluid());
	}
}