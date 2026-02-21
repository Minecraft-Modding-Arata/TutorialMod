package net.arata.tutorialmod.item.custom;

import net.arata.tutorialmod.sound.ModSounds;
import net.arata.tutorialmod.util.ModTags;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MetalDetectorItem extends Item {

    public MetalDetectorItem(Properties properties) {
        super(properties);
    }

    @Override
    public @NotNull InteractionResult useOn(UseOnContext pContext) {

        Level level = pContext.getLevel();
        Player player = pContext.getPlayer();

        if (!level.isClientSide() && player != null) {

            BlockPos positionClicked = pContext.getClickedPos();
            boolean foundBlock = false;

            for (int i = 0; positionClicked.getY() - i >= level.getMinBuildHeight(); i++) {

                BlockState state = level.getBlockState(positionClicked.below(i));

                if (isValuableBlock(state)) {
                    outputValuableCoordinates(positionClicked.below(i), player, state.getBlock());
                    foundBlock = true;
                    pContext.getLevel().playSound(null, positionClicked, ModSounds.METAL_DETECTOR_FOUND_ORE.get(),
                            SoundSource.BLOCKS, 1.0F, 1.0F);
                    break;
                }
            }

            if (!foundBlock) {
                player.sendSystemMessage(Component.literal("No valuable ores found!"));
            }

            pContext.getItemInHand().hurtAndBreak(1, player,
                    p -> p.broadcastBreakEvent(p.getUsedItemHand()));
        }

        return InteractionResult.sidedSuccess(level.isClientSide);
    }

    @Override
    public void appendHoverText(@NotNull ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.tutorialmod.metal_detector"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + ", " + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.METAL_DETECTOR_VALUABLES);
    }
}
