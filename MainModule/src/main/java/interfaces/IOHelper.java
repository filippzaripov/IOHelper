package interfaces;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Набор методов-помощников для работы с вводом-выводом.
 *
 * Реализация должны использовать библиотеку {@code java.io}. Там, где это целесообразно, должна применяться
 * буферизация данных для более эффективной работы.
 */
public interface IOHelper {

    /**
     * Копирует байты из {@code InputStream} в {@code OutputStream}.
     *
     * @param in входной поток
     * @param out выходной поток
     *
     * @return число скопированных байт
     */
    long copy(InputStream in, OutputStream out) throws IOException;

    /**
     * Копирует указанные файл.
     *
     * @param source копируемый файл
     * @param target файл в который копировать
     *
     * @return размер скопированных данных в байтах
     */
    long copy(File source, File target) throws IOException;

    /**
     * Считывает текстовый файл в строку. Файл считывается с использованием системной кодировки.
     *
     * @param file файл для считывания
     *
     * @return содержимое файла в виде строки
     */
    String readFile(File file) throws IOException;

    /**
     * Считывает файл в строку.
     *
     * @param file файл файл для считывания
     * @param encoding кодировка
     *
     * @return содержимое файла в виде строки
     */
    String readFile(File file, String encoding) throws IOException;

    /**
     * Записывает строку в файл.
     *
     * @param file файл для записи
     * @param content содержимое
     * @param encoding кодировка, если {@code null}, то будет использована системная кодировка
     * @param append если {@code true}, то добавить строку к существующему содержимому,
     *               если {@code false}, то перезаписать содержимое
     */
    void writeFile(File file, String content, String encoding, boolean append) throws IOException;

    /**
     * Создает {@code InputStream}, читающий указанную строку.
     *
     * @param source строка для чтения
     * @return {@code InputStream}
     */
    InputStream createInputStream(String source) throws IOException;

    /**
     * Создает {@code InputStream}, читающий указанную строку.
     *
     * @param source строка для чтения
     * @param encoding кодировка
     * @return {@code InputStream}
     */
    InputStream createInputStream(String source, String encoding) throws IOException;

}

