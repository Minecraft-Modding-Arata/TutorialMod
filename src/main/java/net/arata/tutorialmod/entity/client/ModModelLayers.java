package net.arata.tutorialmod.entity.client;

import net.arata.tutorialmod.TutorialMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {

    public static final ModelLayerLocation RHINO_LAYER = new  ModelLayerLocation(
            ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "rhino"), "main"); // Combinación de texturas o algo así
}
