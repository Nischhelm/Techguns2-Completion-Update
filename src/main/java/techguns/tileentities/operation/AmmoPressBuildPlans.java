package techguns.tileentities.operation;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.oredict.OreDictionary;
import techguns.TGItems;
import techguns.tileentities.AmmoPressTileEnt;

import java.util.ArrayList;
import java.util.List;

public class AmmoPressBuildPlans {
    public static final int AMMOUNT_PISTOL = 12;
    public static final int AMMOUNT_SHOTGUN = 16;
    public static final int AMMOUNT_RIFLE = 8;
    public static final int AMMOUNT_SNIPER = 4;
    public static ArrayList<ItemStack> metal1 = new ArrayList<>();
    public static ArrayList<ItemStack> metal2 = new ArrayList<>();
    public static ArrayList<ItemStack> powder = new ArrayList<>();

    public static void init(ArrayList<String> metal1_names, ArrayList<String> metal2_names, ArrayList<String> powder_names) {

        if (metal1_names != null) {
            for (String name : metal1_names) {
                NonNullList<ItemStack> ores = OreDictionary.getOres(name);
                if (!ores.isEmpty()) {
                    metal1.addAll(ores);
                }
            }
        }

        if (metal2_names != null) {
            for (String name : metal2_names) {
                NonNullList<ItemStack> ores = OreDictionary.getOres(name);
                if (!ores.isEmpty()) {
                    for (ItemStack ore : ores) {
                        ItemStack stack = ore.copy();
                        stack.setCount(2);
                        metal2.add(stack);
                    }
                }
            }
        }

        if (powder_names != null) {
            for (String name : powder_names) {
                NonNullList<ItemStack> ores = OreDictionary.getOres(name);
                if (!ores.isEmpty()) {
                    powder.addAll(ores);
                }
            }
        }

    }

    public static boolean isInList(ItemStack it, ArrayList<ItemStack> list) {
        boolean valid = false;
        for (ItemStack itemStack : list) {
            if (OreDictionary.itemMatches(itemStack, it, false)) {
                valid = true;
                break;
            }
        }
        return valid;
    }

    public static boolean isValidFor(ItemStack input, int slot) {
        return switch (slot) {
            case AmmoPressTileEnt.SLOT_METAL1 -> isInList(input, metal1);
            case AmmoPressTileEnt.SLOT_METAL2 -> isInList(input, metal2);
            case AmmoPressTileEnt.SLOT_POWDER -> isInList(input, powder);
            default -> false;
        };
    }

    public static ItemStack getOutputFor(ItemStack metal1, ItemStack metal2, ItemStack powder, int plan) {
        if (isValidFor(metal1, 0) && isValidFor(metal2, 1) && isValidFor(powder, 2)) {

            if (metal1.getCount() >= 1 && metal2.getCount() >= 2 && powder.getCount() >= 1) {

                switch (plan) {
                    case 0:
                        return new ItemStack(TGItems.PISTOL_ROUNDS.getItem(), AMMOUNT_PISTOL, TGItems.PISTOL_ROUNDS.getItemDamage());
                    case 1:
                        return new ItemStack(TGItems.SHOTGUN_ROUNDS.getItem(), AMMOUNT_SHOTGUN, TGItems.SHOTGUN_ROUNDS.getItemDamage());
                    case 2:
                        return new ItemStack(TGItems.RIFLE_ROUNDS.getItem(), AMMOUNT_RIFLE, TGItems.RIFLE_ROUNDS.getItemDamage());
                    case 3:
                        return new ItemStack(TGItems.SNIPER_ROUNDS.getItem(), AMMOUNT_SNIPER, TGItems.SNIPER_ROUNDS.getItemDamage());
                }

            }

        }
        return ItemStack.EMPTY;
    }

    public static IMachineRecipe getRecipeForType(int type) {
        return new AmmoPressMachineRecipe(type);
    }

    public static class AmmoPressMachineRecipe implements IMachineRecipe {

        byte plan;

        public AmmoPressMachineRecipe(int plan) {
            super();
            this.plan = (byte) plan;
        }

        @Override
        public List<List<ItemStack>> getItemInputs() {
            List<List<ItemStack>> inputs = new ArrayList<>();

            inputs.add(metal1);
            inputs.add(metal2);
            inputs.add(powder);

            return inputs;
        }

        @Override
        public List<List<ItemStack>> getItemOutputs() {
            List<List<ItemStack>> outputs = new ArrayList<>();
            ArrayList<ItemStack> output = new ArrayList<>();

            ItemStack stack = ItemStack.EMPTY;
            stack = switch (plan) {
                case 0 -> new ItemStack(TGItems.PISTOL_ROUNDS.getItem(), AMMOUNT_PISTOL, TGItems.PISTOL_ROUNDS.getItemDamage());
                case 1 -> new ItemStack(TGItems.SHOTGUN_ROUNDS.getItem(), AMMOUNT_SHOTGUN, TGItems.SHOTGUN_ROUNDS.getItemDamage());
                case 2 -> new ItemStack(TGItems.RIFLE_ROUNDS.getItem(), AMMOUNT_RIFLE, TGItems.RIFLE_ROUNDS.getItemDamage());
                case 3 -> new ItemStack(TGItems.SNIPER_ROUNDS.getItem(), AMMOUNT_SNIPER, TGItems.SNIPER_ROUNDS.getItemDamage());
                default -> stack;
            };
            output.add(stack);
            outputs.add(output);
            return outputs;
        }
    }
}
