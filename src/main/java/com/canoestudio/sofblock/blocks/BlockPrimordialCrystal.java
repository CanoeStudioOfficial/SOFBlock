package com.canoestudio.sofblock.blocks;

import com.canoestudio.sofblock.SOFBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import thaumcraft.api.ThaumcraftApiHelper;

import java.util.Random;

public class BlockPrimordialCrystal extends Block {

    public static final PropertyDirection FACING = PropertyDirection.create("facing");
    private final EnumPrimalAspect aspectType;

    private static final AxisAlignedBB AABB_UP = new AxisAlignedBB(0.2, 0.0, 0.2, 0.8, 0.85, 0.8);
    private static final AxisAlignedBB AABB_DOWN = new AxisAlignedBB(0.2, 0.15, 0.2, 0.8, 1.0, 0.8);
    private static final AxisAlignedBB AABB_NORTH = new AxisAlignedBB(0.2, 0.2, 0.15, 0.8, 0.8, 1.0);
    private static final AxisAlignedBB AABB_SOUTH = new AxisAlignedBB(0.2, 0.2, 0.0, 0.8, 0.8, 0.85);
    private static final AxisAlignedBB AABB_WEST = new AxisAlignedBB(0.15, 0.2, 0.2, 1.0, 0.8, 0.8);
    private static final AxisAlignedBB AABB_EAST = new AxisAlignedBB(0.0, 0.2, 0.2, 0.85, 0.8, 0.8);

    public BlockPrimordialCrystal(EnumPrimalAspect aspect) {
        super(Material.GLASS, MapColor.DIAMOND);
        this.aspectType = aspect;
        setTranslationKey(SOFBlock.MOD_ID + ".primordial_" + aspect.getName() + "_crystal");
        setRegistryName("primordial_" + aspect.getName() + "_crystal");
        setDefaultState(this.blockState.getBaseState().withProperty(FACING, EnumFacing.UP));
        setHardness(3.0f);
        setResistance(5.0f);
        setLightLevel(1.0f);
        setSoundType(SoundType.GLASS);
        setHarvestLevel("pickaxe", 1);
        setCreativeTab(CreativeTabs.DECORATIONS);

        ModBlocks.registerBlock(this);
    }

    public EnumPrimalAspect getAspectType() {
        return aspectType;
    }

    @SideOnly(Side.CLIENT)
    public BlockRenderLayer getRenderLayer() {
        return BlockRenderLayer.TRANSLUCENT;
    }

    @Override
    protected boolean canSilkHarvest() {
        return true;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isTopSolid(IBlockState state) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean doesSideBlockRendering(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing face) {
        return false;
    }

    @Override
    public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess source, BlockPos pos) {
        switch (state.getValue(FACING)) {
            case UP:    return AABB_UP;
            case DOWN:  return AABB_DOWN;
            case NORTH: return AABB_NORTH;
            case SOUTH: return AABB_SOUTH;
            case WEST:  return AABB_WEST;
            case EAST:  return AABB_EAST;
            default:    return AABB_UP;
        }
    }

    @Override
    public IBlockState getStateForPlacement(World worldIn, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        EnumFacing facing = EnumFacing.byIndex(meta);
        return this.getDefaultState().withProperty(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        EnumFacing facing = state.getValue(FACING);
        return facing.getIndex();
    }

    @Override
    public IBlockState withRotation(IBlockState state, Rotation rot) {
        return state.withProperty(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public IBlockState withMirror(IBlockState state, Mirror mirrorIn) {
        return state.withRotation(mirrorIn.toRotation(state.getValue(FACING)));
    }

    @Override
    protected BlockStateContainer createBlockState() {
        return new BlockStateContainer(this, FACING);
    }

    @Override
    public int quantityDroppedWithBonus(int fortune, Random random) {
        return Math.max(1, Math.min(6, this.quantityDropped(random) + random.nextInt(fortune + 1)));
    }

    @Override
    public int quantityDropped(Random random) {
        return 1 + random.nextInt(3);
    }

    @Override
    public Item getItemDropped(IBlockState state, Random rand, int fortune) {
        return null;
    }

    @Override
    public void dropBlockAsItemWithChance(World worldIn, BlockPos pos, IBlockState state, float chance, int fortune) {
        if (!worldIn.isRemote && !worldIn.restoringBlockSnapshots) {
            ItemStack crystalDrop = ThaumcraftApiHelper.makeCrystal(this.aspectType.getThaumcraftAspect(), this.quantityDroppedWithBonus(fortune, worldIn.rand));
            if (crystalDrop != null && !crystalDrop.isEmpty()) {
                spawnAsEntity(worldIn, pos, crystalDrop);
            }
        }
    }

    @Override
    public void getDrops(net.minecraft.util.NonNullList<ItemStack> drops, IBlockAccess world, BlockPos pos, IBlockState state, int fortune) {
        ItemStack crystalDrop = ThaumcraftApiHelper.makeCrystal(this.aspectType.getThaumcraftAspect(), this.quantityDroppedWithBonus(fortune, RANDOM));
        if (crystalDrop != null && !crystalDrop.isEmpty()) {
            drops.add(crystalDrop);
        }
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        for (EnumFacing facing : EnumFacing.values()) {
            if (world.getBlockState(pos.offset(facing)).isNormalCube()) {
                return true;
            }
        }
        return super.canPlaceBlockAt(world, pos);
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block blockIn, BlockPos fromPos) {
        EnumFacing facing = state.getValue(FACING);
        BlockPos attachPos = pos.offset(facing.getOpposite());
        if (!world.getBlockState(attachPos).isNormalCube()) {
            dropBlockAsItem(world, pos, state, 0);
            world.setBlockToAir(pos);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(4) == 0) {
            EnumFacing facing = stateIn.getValue(FACING);
            double d0 = pos.getX() + 0.5 + (rand.nextDouble() - 0.5) * 0.4;
            double d1 = pos.getY() + 0.5 + (rand.nextDouble() - 0.5) * 0.4;
            double d2 = pos.getZ() + 0.5 + (rand.nextDouble() - 0.5) * 0.4;
            double motionX = facing.getXOffset() * 0.02;
            double motionY = facing.getYOffset() * 0.02 + 0.01;
            double motionZ = facing.getZOffset() * 0.02;
            worldIn.spawnParticle(EnumParticleTypes.END_ROD, d0, d1, d2, motionX, motionY, motionZ);
        }
    }

    @Override
    public int getLightValue(IBlockState state, IBlockAccess world, BlockPos pos) {
        return 15;
    }
}
