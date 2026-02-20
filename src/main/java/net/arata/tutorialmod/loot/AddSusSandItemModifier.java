package net.arata.tutorialmod.loot;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class AddSusSandItemModifier extends LootModifier {

    public static final Supplier<Codec<AddSusSandItemModifier>> CODEC = Suppliers.memoize(()
            -> RecordCodecBuilder.create(instance -> codecStart(instance).and(ForgeRegistries.ITEMS.getCodec()
                    .fieldOf("item").forGetter(m -> m.item)).apply(instance, AddSusSandItemModifier::new)));
    private final Item item;

    /**
     * Constructs a LootModifier.
     *
     * @param conditionsIn the ILootConditions that need to be matched before the loot is modified.
     */
    // Aquí estamos añadiendo un solo item, pero si queremos cambiarlo también se puede
    public AddSusSandItemModifier(LootItemCondition[] conditionsIn, Item item) {
        super(conditionsIn);
        this.item = item;
    }

    @Override
    protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {

        for (LootItemCondition lootCondition : this.conditions) {
            if (!lootCondition.test(context)) {
                return generatedLoot;
            }
        }

        if(context.getRandom().nextFloat() < 0.5f) { // 50% chance
            generatedLoot.clear();
            generatedLoot.add(new ItemStack(this.item));
        }

        return generatedLoot;
    }

    @Override
    public Codec<? extends IGlobalLootModifier> codec() {
        return CODEC.get();
    }
}
