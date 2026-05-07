package com.canoestudio.sofblock.world;

import com.canoestudio.sofblock.blocks.BlockPrimordialCrystal;
import com.canoestudio.sofblock.blocks.EnumPrimalAspect;
import com.canoestudio.sofblock.blocks.ModBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class WorldGenPrimordialCrystal implements IWorldGenerator {

    private static final int GENERATE_CHANCE = 8;
    private static final int MIN_Y = 5;
    private static final int MAX_Y = 48;
    private static final int CLUSTER_SIZE = 3;

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() != 0) {
            return;
        }

        if (random.nextInt(GENERATE_CHANCE) != 0) {
            return;
        }

        int baseX = chunkX * 16 + random.nextInt(16);
        int baseZ = chunkZ * 16 + random.nextInt(16);
        int baseY = MIN_Y + random.nextInt(MAX_Y - MIN_Y);

        EnumPrimalAspect aspect = getAspectForBiome(world, new BlockPos(baseX, baseY, baseZ), random);

        int count = 1 + random.nextInt(CLUSTER_SIZE);
        for (int i = 0; i < count; i++) {
            int x = baseX + random.nextInt(6) - 3;
            int y = baseY + random.nextInt(4) - 2;
            int z = baseZ + random.nextInt(6) - 3;
            BlockPos pos = new BlockPos(x, y, z);

            if (world.isAirBlock(pos)) {
                EnumFacing attachDir = findAttachDirection(world, pos);
                if (attachDir != null) {
                    BlockPrimordialCrystal crystal = ModBlocks.getPrimordialCrystal(aspect);
                    IBlockState state = crystal.getDefaultState()
                            .withProperty(BlockPrimordialCrystal.FACING, attachDir);
                    world.setBlockState(pos, state, 2);
                }
            }
        }
    }

    private EnumFacing findAttachDirection(World world, BlockPos pos) {
        for (EnumFacing facing : EnumFacing.values()) {
            BlockPos neighbor = pos.offset(facing);
            IBlockState neighborState = world.getBlockState(neighbor);
            if (neighborState.isNormalCube()) {
                return facing.getOpposite();
            }
        }
        return null;
    }

    private EnumPrimalAspect getAspectForBiome(World world, BlockPos pos, Random random) {
        net.minecraft.world.biome.Biome biome = world.getBiome(pos);
        net.minecraft.world.biome.Biome.TempCategory temp = biome.getTempCategory();

        if (biome.isHighHumidity()) {
            if (random.nextFloat() < 0.6f) return EnumPrimalAspect.AQUA;
        }

        if (temp == net.minecraft.world.biome.Biome.TempCategory.WARM) {
            if (random.nextFloat() < 0.5f) return EnumPrimalAspect.IGNIS;
        }

        if (temp == net.minecraft.world.biome.Biome.TempCategory.COLD) {
            if (random.nextFloat() < 0.5f) return EnumPrimalAspect.AER;
        }

        if (biome.getBaseHeight() > 1.0f) {
            if (random.nextFloat() < 0.4f) return EnumPrimalAspect.ORDO;
        }

        if (biome.getBaseHeight() < -0.5f) {
            if (random.nextFloat() < 0.4f) return EnumPrimalAspect.PERDITIO;
        }

        if (random.nextFloat() < 0.3f) return EnumPrimalAspect.TERRA;

        return EnumPrimalAspect.getRandom(random);
    }
}
