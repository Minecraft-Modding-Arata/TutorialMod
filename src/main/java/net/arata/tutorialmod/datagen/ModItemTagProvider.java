package net.arata.tutorialmod.datagen;

import net.arata.tutorialmod.TutorialMod;
import net.arata.tutorialmod.block.ModBlocks;
import net.arata.tutorialmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, TutorialMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR).add(ModItems.SAPPHIRE_HELMET.get(), ModItems.SAPPHIRE_CHESTPLATE.get(),
                ModItems.SAPPHIRE_LEGGINGS.get(), ModItems.SAPPHIRE_BOOTS.get());

        this.tag(ItemTags.MUSIC_DISCS).add(ModItems.BAR_BRAWL_MUSIC_DISC.get());
        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(ModItems.BAR_BRAWL_MUSIC_DISC.get());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.PINE_LOG.get().asItem(), ModBlocks.STRIPPED_PINE_LOG.get().asItem(),
                        ModBlocks.PINE_WOOD.get().asItem(), ModBlocks.STRIPPED_PINE_WOOD.get().asItem());
        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.PINE_PLANKS.get().asItem()); // Permite crear palos y otras cosas generales con la madera
    }
}
