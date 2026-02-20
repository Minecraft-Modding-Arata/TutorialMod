package net.arata.tutorialmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {

    public static final FoodProperties STRAWBERRY = new FoodProperties.Builder().nutrition(2).fast()
            .saturationMod(0.2f).effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200), 0.1f)
            .build(); // No podemos acceder a la entidad en sí, sino a su Builder (diseño Builder)

    public static final FoodProperties CORN = new FoodProperties.Builder().nutrition(3)
            .saturationMod(0.2f).build();
}
