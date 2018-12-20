package be.mo.consult.sandbox;

import java.util.UUID;

public class _UUID {

    public static void main(String[] args) {
        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);
        UUID uuid1 = UUID.randomUUID();
        System.out.println(uuid1.toString());
        UUID uuid2 = UUID.randomUUID();
        System.out.println(uuid2);
    }
}
