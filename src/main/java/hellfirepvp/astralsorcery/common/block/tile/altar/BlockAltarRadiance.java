/*******************************************************************************
 * HellFirePvP / Astral Sorcery 2019
 *
 * All rights reserved.
 * The source code is available on github: https://github.com/HellFirePvP/AstralSorcery
 * For further details, see the License file there.
 ******************************************************************************/

package hellfirepvp.astralsorcery.common.block.tile.altar;

import hellfirepvp.astralsorcery.common.block.tile.BlockAltar;
import hellfirepvp.astralsorcery.common.util.VoxelUtils;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: BlockAltarRadiance
 * Created by HellFirePvP
 * Date: 12.08.2019 / 21:59
 */
public class BlockAltarRadiance extends BlockAltar {

    private final VoxelShape shape;

    public BlockAltarRadiance() {
        super(AltarType.RADIANCE);
        this.shape = createShape();
    }

    protected VoxelShape createShape() {
        VoxelShape base = Block.makeCuboidShape(0, 0, 0, 16, 4, 16);
        VoxelShape pillar = Block.makeCuboidShape(4, 4, 4, 12, 8, 12);
        VoxelShape head = Block.makeCuboidShape(0, 8, 0, 16, 16, 16);

        return VoxelUtils.combineAll(IBooleanFunction.OR, base, pillar, head);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return this.shape;
    }
}