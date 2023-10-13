package com.andersen.carservice.util;

import lombok.experimental.UtilityClass;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.UUID;

@UtilityClass
public class UuidGenerator {
    private final SecureRandom random = new SecureRandom();

    public static UUID generate() {
        byte[] randomBytes = new byte[16];
        random.nextBytes(randomBytes);

        randomBytes[6] &= 0x0f;
        randomBytes[6] |= 0x40;
        randomBytes[8] &= 0x3f;
        randomBytes[8] |= (byte) 0x80;

        return new UUID(ByteBuffer.wrap(randomBytes).getLong(), ByteBuffer.wrap(randomBytes, 8, 8).getLong());
    }
}
