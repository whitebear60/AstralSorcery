/*******************************************************************************
 * HellFirePvP / Astral Sorcery 2019
 *
 * All rights reserved.
 * The source code is available on github: https://github.com/HellFirePvP/AstralSorcery
 * For further details, see the License file there.
 ******************************************************************************/

package hellfirepvp.astralsorcery.common.lib;

import hellfirepvp.astralsorcery.AstralSorcery;
import hellfirepvp.astralsorcery.common.crafting.serializer.LiquidInfusionSerializer;
import hellfirepvp.astralsorcery.common.crafting.serializer.SimpleAltarRecipeSerializer;
import hellfirepvp.astralsorcery.common.crafting.serializer.WellRecipeSerializer;
import net.minecraft.util.ResourceLocation;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: RecipeSerializersAS
 * Created by HellFirePvP
 * Date: 30.06.2019 / 23:32
 */
public class RecipeSerializersAS {

    private RecipeSerializersAS() {}

    public static final ResourceLocation WELL_LIQUEFACTION = AstralSorcery.key("lightwell");
    public static final ResourceLocation LIQUID_INFUSION = AstralSorcery.key("liquid_infuser");
    public static final ResourceLocation SIMPLE_ALTAR_CRAFTING = AstralSorcery.key("altar");

    public static WellRecipeSerializer WELL_LIQUEFACTION_SERIALIZER;
    public static LiquidInfusionSerializer LIQUID_INFUSION_SERIALIZER;
    public static SimpleAltarRecipeSerializer ALTAR_RECIPE_SERIALIZER;

}