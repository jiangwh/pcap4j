/*_##########################################################################
  _##
  _##  Copyright (C) 2016  Pcap4J.org
  _##
  _##########################################################################
*/

package org.pcap4j.packet;

import org.pcap4j.packet.RadiotapPacket.RadiotapDataField;
import org.pcap4j.util.ByteArrays;

/**
 * RF signal power at the antenna, decibel difference from an arbitrary, fixed reference.
 * This field contains a single unsigned 8-bit value.
 *
 * @see <a href="http://www.radiotap.org/defined-fields/dB%20antenna%20signal">Radiotap</a>
 * @author Kaito Yamada
 * @since pcap4j 1.6.5
 */
public final class RadiotapDbAntennaSignal implements RadiotapDataField {

  /**
   *
   */
  private static final long serialVersionUID = 6965646323859387882L;

  private static final int LENGTH = 1;

  private final byte antennaSignal;

  /**
   * A static factory method.
   * This method validates the arguments by {@link ByteArrays#validateBounds(byte[], int, int)},
   * which may throw exceptions undocumented here.
   *
   * @param rawData rawData
   * @param offset offset
   * @param length length
   * @return a new RadiotapDbAntennaSignal object.
   * @throws IllegalRawDataException if parsing the raw data fails.
   */
  public static RadiotapDbAntennaSignal newInstance(
    byte[] rawData, int offset, int length
  ) throws IllegalRawDataException {
    ByteArrays.validateBounds(rawData, offset, length);
    return new RadiotapDbAntennaSignal(rawData, offset, length);
  }

  private RadiotapDbAntennaSignal(byte[] rawData, int offset, int length) throws IllegalRawDataException {
    if (length < LENGTH) {
      StringBuilder sb = new StringBuilder(200);
      sb.append("The data is too short to build a RadiotapDbAntennaSignal (")
        .append(LENGTH)
        .append(" bytes). data: ")
        .append(ByteArrays.toHexString(rawData, " "))
        .append(", offset: ")
        .append(offset)
        .append(", length: ")
        .append(length);
      throw new IllegalRawDataException(sb.toString());
    }

    this.antennaSignal = ByteArrays.getByte(rawData, offset);
  }

  private RadiotapDbAntennaSignal(Builder builder) {
    if (builder == null) {
      throw new NullPointerException("builder is null.");
    }

    this.antennaSignal = builder.antennaSignal;
  }

  /**
   * @return antennaSignal (unit: dB)
   */
  public byte getAntennaSignal() { return antennaSignal; }

  /**
   * @return antennaSignal (unit: dB)
   */
  public int getAntennaSignalAsInt() { return antennaSignal & 0xFF; }

  @Override
  public int length() {
    return LENGTH;
  }

  @Override
  public byte[] getRawData() {
    return ByteArrays.toByteArray(antennaSignal);
  }

  /**
   * @return a new Builder object populated with this object's fields.
   */
  public Builder getBuilder() { return new Builder(this); }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("[Antenna signal: ")
      .append(getAntennaSignalAsInt())
      .append(" dB]");

    return sb.toString();
  }

  @Override
  public int hashCode() {
    return antennaSignal;
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == this) { return true; }
    if (!this.getClass().isInstance(obj)) { return false; }
    RadiotapDbAntennaSignal other = (RadiotapDbAntennaSignal) obj;
    return antennaSignal == other.antennaSignal;
  }

  /**
   * @author Kaito Yamada
   * @since pcap4j 1.6.5
   */
  public static final class Builder {

    private byte antennaSignal;

    /**
     *
     */
    public Builder() {}

    private Builder(RadiotapDbAntennaSignal obj) {
      this.antennaSignal = obj.antennaSignal;
    }

    /**
     * @param antennaSignal antennaSignal
     * @return this Builder object for method chaining.
     */
    public Builder antennaSignal(byte antennaSignal) {
      this.antennaSignal = antennaSignal;
      return this;
    }

    /**
     * @return a new RadiotapDbAntennaSignal object.
     */
    public RadiotapDbAntennaSignal build() {
      return new RadiotapDbAntennaSignal(this);
    }

  }

}
