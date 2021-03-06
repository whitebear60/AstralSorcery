/*******************************************************************************
 * HellFirePvP / Astral Sorcery 2019
 *
 * All rights reserved.
 * The source code is available on github: https://github.com/HellFirePvP/AstralSorcery
 * For further details, see the License file there.
 ******************************************************************************/

package hellfirepvp.astralsorcery.common.constellation.perk.attribute.type;

import hellfirepvp.astralsorcery.common.constellation.perk.PerkAttributeHelper;
import hellfirepvp.astralsorcery.common.constellation.perk.attribute.AttributeTypeRegistry;
import hellfirepvp.astralsorcery.common.constellation.perk.attribute.PerkAttributeType;
import hellfirepvp.astralsorcery.common.data.research.ResearchManager;
import hellfirepvp.astralsorcery.common.event.AttributeEvent;
import hellfirepvp.astralsorcery.common.event.PotionApplyEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

/**
 * This class is part of the Astral Sorcery Mod
 * The complete source code for this mod can be found on github.
 * Class: AttributePotionDuration
 * Created by HellFirePvP
 * Date: 26.10.2018 / 23:04
 */
public class AttributePotionDuration extends PerkAttributeType {

    public AttributePotionDuration() {
        super(AttributeTypeRegistry.ATTR_TYPE_POTION_DURATION, true);
    }

    @SubscribeEvent
    public void onPotionDurationNew(PotionApplyEvent.New event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            modifyPotionDuration((EntityPlayer) event.getEntityLiving(), event.getPotionEffect(), event.getPotionEffect());
        }
    }

    @SubscribeEvent
    public void onPotionDurationChanged(PotionApplyEvent.Changed event) {
        if (event.getEntityLiving() instanceof EntityPlayer) {
            modifyPotionDuration((EntityPlayer) event.getEntityLiving(), event.getNewCombinedEffect(), event.getAddedEffect());
        }
    }

    private void modifyPotionDuration(EntityPlayer player, PotionEffect newSetEffect, PotionEffect addedEffect) {
        if (player.world.isRemote ||
                newSetEffect.getPotion().isBadEffect() ||
                addedEffect.getAmplifier() < newSetEffect.getAmplifier()) {
            return;
        }

        float existingDuration = addedEffect.getDuration();
        float newDuration = PerkAttributeHelper.getOrCreateMap(player, Side.SERVER)
                .modifyValue(player, ResearchManager.getProgress(player, Side.SERVER), AttributeTypeRegistry.ATTR_TYPE_POTION_DURATION, existingDuration);
        newDuration = AttributeEvent.postProcessModded(player, this, newDuration);

        if (newSetEffect.getDuration() < newDuration) {
            newSetEffect.duration = MathHelper.floor(newDuration);
        }
    }

}
