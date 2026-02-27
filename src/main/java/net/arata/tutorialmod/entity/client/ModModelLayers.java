package net.arata.tutorialmod.entity.client;

import net.arata.tutorialmod.TutorialMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {

    public static final ModelLayerLocation RHINO_LAYER = new  ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "rhino"), "main"); // Combinación de texturas o algo así

    public static final ModelLayerLocation PINE_HANGING_SIGN_LAYER = new  ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "pine_hanging_sign"), "main");
}
