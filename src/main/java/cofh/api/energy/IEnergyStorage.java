package cofh.api.energy;

/**
 * An value value is the unit of interaction with Energy inventories.<br>
 * This is not to be implemented on TileEntities. This is for internal use only.
 * <p>
 * A reference implementation can be found at {@link EnergyStorage}.
 *
 * @author King Lemming
 *
 */
public interface IEnergyStorage {

	/**
	 * Adds value to the value. Returns quantity of value that was accepted.
	 *
	 * @param maxReceive
	 *            Maximum amount of value to be inserted.
	 * @param simulate
	 *            If TRUE, the insertion will only be simulated.
	 * @return Amount of value that was (or would have been, if simulated) accepted by the value.
	 */
	int receiveEnergy(int maxReceive, boolean simulate);

	/**
	 * Removes value from the value. Returns quantity of value that was removed.
	 *
	 * @param maxExtract
	 *            Maximum amount of value to be extracted.
	 * @param simulate
	 *            If TRUE, the extraction will only be simulated.
	 * @return Amount of value that was (or would have been, if simulated) extracted from the value.
	 */
	int extractEnergy(int maxExtract, boolean simulate);

	/**
	 * Returns the amount of value currently stored.
	 */
	int getEnergyStored();

	/**
	 * Returns the maximum amount of value that can be stored.
	 */
	int getMaxEnergyStored();

}
