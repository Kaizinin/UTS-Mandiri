# Hello BPMN – Spring Boot + Flowable

Tugas 2: instalasi dan konfigurasi tools BPMN pada lingkungan Java/Jakarta,
dibuktikan dengan proses *Hello World*.

- **Nama / NIM:** (isi)
- **Mata kuliah:** (isi)
- **Tools:** Java 17, Maven, Spring Boot 3.4.x, Flowable 7.2.0 (engine BPMN), H2 (database in-memory), bpmn.io (modeler)

## 1. Prasyarat dan instalasi

| Tools | Versi | Cara memasang | Cek |
|---|---|---|---|
| JDK | 17 (Eclipse Temurin) | adoptium.net, centang *Set JAVA_HOME* dan *Add to PATH* | `java -version` |
| Maven | 3.9+ | maven.apache.org/download.cgi, ekstrak, tambahkan folder `bin` ke `Path` | `mvn -v` |
| IDE | IntelliJ IDEA Community / VS Code | jetbrains.com/idea | buka project ini |
| Modeler BPMN | bpmn.io | buka demo.bpmn.io di browser (tanpa instalasi) | diagram tampil |

Flowable tidak diinstal manual. Library-nya diunduh otomatis oleh Maven dari
dependency di `pom.xml`.

## 2. Struktur project

```
hello-bpmn/
├── pom.xml                                   # dependency Flowable + H2
├── src/main/java/id/ac/uinjkt/hellobpmn/
│   ├── HelloBpmnApplication.java             # titik masuk, memulai proses
│   └── HelloDelegate.java                    # kode Java yang dipanggil service task
├── src/main/resources/
│   ├── application.properties                # konfigurasi database dan Flowable
│   └── processes/hello.bpmn20.xml            # model BPMN
└── docs/screenshots/                         # taruh bukti screenshot di sini
```

## 3. Konfigurasi

1. `pom.xml` menambahkan `flowable-spring-boot-starter-process` dan `h2`.
2. `application.properties` mengatur database H2 in-memory dan lokasi file proses.
3. File BPMN diletakkan di `src/main/resources/processes/` sehingga
   otomatis di-*deploy* saat aplikasi start.

## 4. Cara menjalankan

```bash
mvn spring-boot:run
```

Hasil yang diharapkan di console:

```
>>> Hello World dari BPMN! Nama: Mahasiswa
>>> Proses dimulai, id = ...
```

## 5. Cara kerja singkat

1. Aplikasi start, Flowable membuat tabel di H2 dan men-deploy `hello.bpmn20.xml`.
2. `CommandLineRunner` memanggil `startProcessInstanceByKey("helloProcess", ...)`.
3. Engine menjalankan alur: **Mulai → Cetak Hello World → Selesai**.
4. Service task memanggil bean `helloDelegate` (`HelloDelegate.execute`),
   yang mencetak teks beserta variabel proses `nama`.

## 6. Bukti pengujian

Simpan di `docs/screenshots/`:

1. `java -version` dan `mvn -v`
2. Diagram BPMN di demo.bpmn.io
3. Output console hello world
4. Rekaman layar proses menjalankan aplikasi

## 7. Kendala dan solusi

(Isi dengan kendala yang kamu alami sendiri, misalnya `mvn` tidak dikenali
karena Path belum diatur, beserta cara mengatasinya.)
