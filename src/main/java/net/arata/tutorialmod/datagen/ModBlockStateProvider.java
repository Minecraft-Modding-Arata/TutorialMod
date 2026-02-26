package net.arata.tutorialmod.datagen;

import net.arata.tutorialmod.TutorialMod;
import net.arata.tutorialmod.block.ModBlocks;
import net.arata.tutorialmod.block.custom.CornCropBlock;
import net.arata.tutorialmod.block.custom.StrawberryCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TutorialMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.SAPPHIRE_BLOCK);
        blockWithItem(ModBlocks.RAW_SAPPHIRE_BLOCK);

        blockWithItem(ModBlocks.SAPPHIRE_ORE);
        blockWithItem(ModBlocks.DEEPSLATE_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.NETHER_SAPPHIRE_ORE);
        blockWithItem(ModBlocks.END_STONE_SAPPHIRE_ORE);

        blockWithItem(ModBlocks.SOUND_BLOCK);

        stairsBlock((StairBlock) ModBlocks.SAPPHIRE_STAIRS.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        slabBlock((SlabBlock) ModBlocks.SAPPHIRE_SLAB.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        buttonBlock((ButtonBlock) ModBlocks.SAPPHIRE_BUTTON.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        pressurePlateBlock((PressurePlateBlock) ModBlocks.SAPPHIRE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        fenceBlock((FenceBlock) ModBlocks.SAPPHIRE_FENCE.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        fenceGateBlock((FenceGateBlock) ModBlocks.SAPPHIRE_FENCE_GATE.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));
        wallBlock((WallBlock) ModBlocks.SAPPHIRE_WALL.get(), blockTexture(ModBlocks.SAPPHIRE_BLOCK.get()));

        // Se utiliza cutout para poder ver a través de ellas
        doorBlockWithRenderType((DoorBlock) ModBlocks.SAPPHIRE_DOOR.get(), modLoc("block/sapphire_door_bottom"),
                modLoc("block/sapphire_door_top"), "cutout");
        trapdoorBlockWithRenderType((TrapDoorBlock) ModBlocks.SAPPHIRE_TRAPDOOR.get(), modLoc("block/sapphire_trapdoor"),
                true, "cutout");

        // No es lo ideal llamar directamente a un atributo, pero puesto que es public es la forma más sencilla de hacerlo que he encontrado
        makeCrop(ModBlocks.STRAWBERRY_CROP.get(), StrawberryCropBlock.AGE, "strawberry_stage_");
        makeCrop(ModBlocks.CORN_CROP.get(), CornCropBlock.AGE, "corn_stage_");

        simpleBlock(ModBlocks.CATMINT.get(), models().cross(blockTexture(ModBlocks.CATMINT.get()).getPath(),
                blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_CATMINT.get(), models().singleTexture("potted_catmint",
                ResourceLocation.parse("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.CATMINT.get())).renderType("cutout"));

        simpleBlockWithItem(ModBlocks.GEM_POLISHING_STATION.get(), new ModelFile.UncheckedModelFile(modLoc("block/gem_polishing_station")));

        logBlock((RotatedPillarBlock) ModBlocks.PINE_LOG.get());
        axisBlock((RotatedPillarBlock) ModBlocks.PINE_WOOD.get(),  blockTexture(ModBlocks.PINE_LOG.get()),  blockTexture(ModBlocks.PINE_LOG.get()));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_PINE_LOG.get(),  blockTexture(ModBlocks.STRIPPED_PINE_LOG.get()),
                ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "block/stripped_pine_log_top"));
        axisBlock((RotatedPillarBlock) ModBlocks.STRIPPED_PINE_WOOD.get(),  blockTexture(ModBlocks.STRIPPED_PINE_LOG.get()),
                blockTexture(ModBlocks.STRIPPED_PINE_LOG.get()));

        blockItem(ModBlocks.PINE_LOG);
        blockItem(ModBlocks.PINE_WOOD);
        blockItem(ModBlocks.STRIPPED_PINE_LOG);
        blockItem(ModBlocks.STRIPPED_PINE_WOOD);

        blockWithItem(ModBlocks.PINE_PLANKS);

        leavesBlock(ModBlocks.PINE_LEAVES);
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(),
                        ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void blockItem(RegistryObject<Block> blockRegistryObject){
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(TutorialMod.MOD_ID +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    public void makeCrop(CropBlock block, IntegerProperty ageProperty, String modelName) {

        getVariantBuilder(block).forAllStates(state -> {

            int age = state.getValue(ageProperty);

            return new ConfiguredModel[]{
                    new ConfiguredModel(
                            models().crop(
                                    modelName + age,
                                    ResourceLocation.fromNamespaceAndPath(
                                            TutorialMod.MOD_ID,
                                            "block/" + modelName + age // Aquí el modelName se utiliza para la textura, ya que son iguales
                                    )
                            ).renderType("cutout")
                    )
            };
        });
    }
}
