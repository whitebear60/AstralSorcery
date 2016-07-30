package hellfirepvp.astralsorcery.common.world;

import hellfirepvp.astralsorcery.common.block.BlockCustomOre;
import hellfirepvp.astralsorcery.common.block.BlockMarble;
import hellfirepvp.astralsorcery.common.data.config.Config;
import hellfirepvp.astralsorcery.common.lib.BlocksAS;
import net.minecraft.block.BlockStone;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkGenerator;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: AstralWorldGenerator
 * Created by HellFirePvP
 * Date: 07.05.2016 / 19:21
 */
public class AstralWorldGenerator implements IWorldGenerator {

    private WorldGenMinable mineable;

    public AstralWorldGenerator init() {
        if(Config.marbleAmount > 0) {
            mineable = new WorldGenMinable(
                    BlocksAS.blockMarble.getDefaultState().
                            withProperty(BlockMarble.MARBLE_TYPE, BlockMarble.MarbleBlockType.RAW),
                    Config.marbleVeinSize);
        }
        return this;
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (world.provider.getDimension() != 0) return;

        int oX = chunkX * 16;
        int oZ = chunkZ * 16;

        genCrystals(random, chunkX, chunkZ, world);
        if(mineable != null) {
            for (int i = 0; i < Config.marbleAmount; i++) {
                int rX = oX + random.nextInt(16);
                int rY = 50 + random.nextInt(10);
                int rZ = oZ + random.nextInt(16);
                BlockPos pos = new BlockPos(rX, rY, rZ);
                mineable.generate(world, random, pos);
            }
        }
    }

    private void genCrystals(Random random, int chunkX, int chunkZ, World world) {
        if (Config.crystalDensity <= 0 || random.nextInt(Config.crystalDensity) == 0) {
            int xPos = (chunkX << 4) + random.nextInt(16);
            int zPos = (chunkZ << 4) + random.nextInt(16);
            int yPos = 2 + random.nextInt(4);
            BlockPos pos = new BlockPos(xPos, yPos, zPos);
            IBlockState state = world.getBlockState(pos);
            if (state.getBlock().equals(Blocks.stone)) {
                BlockStone.EnumType stoneType = state.getValue(BlockStone.VARIANT);
                if (stoneType != null && stoneType.equals(BlockStone.EnumType.STONE)) {
                    IBlockState newState = BlocksAS.customOre.getDefaultState().withProperty(BlockCustomOre.ORE_TYPE, BlockCustomOre.OreType.CRYSTAL);
                    world.setBlockState(pos, newState);
                }
            }
        }
    }

}
