package com.tr1nks.safevault.util;

import java.io.*;

/**
 * Сериализатор
 * Created by Tr1nks on 07.05.2017.
 */
public class Serializer {

    /**
     * Записать сереализуемый объект в массив байт
     *
     * @param object сереализуемый объект
     * @return массив байт
     */
    public static byte[] writeInBytes(Serializable object) {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            writeInOutputStream(object, bos);
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Записывает сериализуемый объект в выходной поток
     *
     * @param object       серевлизуемый объект
     * @param outputStream выходной поток
     */
    public static void writeInOutputStream(Serializable object, OutputStream outputStream) {
        try (ObjectOutputStream oos = new ObjectOutputStream(outputStream)) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Считывает сериализованный объект из входного потока в объект
     *
     * @param inputStream входной поток
     * @return считанный объект
     * @throws InvalidClassException в случаях несовместимости версий обектов {@link InvalidClassException}
     */
    public static Serializable readFrInputStream(InputStream inputStream) throws InvalidClassException {
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            return (Serializable) ois.readObject();
        } catch (InvalidClassException e) {
            throw e;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Считывает сериализованный объект из входного массива байт
     *
     * @param inArr входной массив байт
     * @return считанный объект
     */
    public static Serializable readFrBytes(byte[] inArr) {
        try (ByteArrayInputStream bis = new ByteArrayInputStream(inArr)) {
            return readFrInputStream(bis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
