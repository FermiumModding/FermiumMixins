package fermiummixins.mixin.itemphysics.vanilla;

import com.creativemd.creativecore.common.packet.PacketHandler;
import com.creativemd.itemphysic.EventHandler;
import com.creativemd.itemphysic.ItemDummyContainer;
import com.creativemd.itemphysic.ItemPhysicClient;
import com.creativemd.itemphysic.ItemTransformer;
import com.creativemd.itemphysic.packet.PickupPacket;
import com.llamalad7.mixinextras.injector.wrapmethod.WrapMethod;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.multiplayer.PlayerControllerMP;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Minecraft.class)
public abstract class Minecraft_FullHandsPickupMixin {

	@Shadow public EntityPlayerSP player;
	@Shadow public PlayerControllerMP playerController;
	@Shadow public RayTraceResult objectMouseOver;
	@Shadow private int rightClickDelayTimer;

	//ItemPhysic only picks up in RightClickBlock, EntityInteract and RightClickEmpty, which never fire with both hands full and nothing targeted
	@WrapMethod(
			method = "rightClickMouse"
	)
	private void fermiummixins_vanillaMinecraft_rightClickMouse(Operation<Void> original) {
		if(this.player != null && !this.playerController.getIsHittingBlock() && !this.player.isRowingBoat()
				&& !this.player.getHeldItemMainhand().isEmpty() && !this.player.getHeldItemOffhand().isEmpty()
				&& (this.objectMouseOver == null || this.objectMouseOver.typeOfHit == RayTraceResult.Type.MISS)
				&& !ItemTransformer.isLite && ItemDummyContainer.CONFIG.pickup.customPickup && ItemPhysicClient.pickup.getKeyCode() == 0) {
			RayTraceResult result = EventHandler.getEntityItem(this.player);
			if(result != null && result.entityHit instanceof EntityItem) {
				this.rightClickDelayTimer = 4;
				this.player.swingArm(EnumHand.MAIN_HAND);
				PacketHandler.sendPacketToServer(new PickupPacket(result.entityHit.getUniqueID(), false));
				return;
			}
		}
		original.call();
	}
}
