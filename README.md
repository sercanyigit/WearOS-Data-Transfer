WearOS, Jetpack Compose, Data Client, P2P Connection

------------------------------------------------------------------------------------------------------------------------------------------------------------------------


- The watch and phone must be paired and connected via Bluetooth.
- The watch and phone applications must have the same package name.
- If signing is performed, both the watch and the phone must be signed with the same keystore.
- Data received on both the watch and phone can be accessed through the onDataChanged() method. Relevant data is written to the specified path. This method will not be called again if the data is not unique. Every time we want to send data, we should send it with an unique value.


------------------------------------------------------------------------------------------------------------------------------------------------------------------------

- Saat ve telefon eşleştirilerek bluetooth bağlantısı yapılmalıdır.
- Saat ve telefon uygulaması aynı paket adına sahip olmalıdır.
- İmzalama işlemi yapılacaksa hem saat hemde telefon aynı keystore ile imzalanmalıdır.
- onDataChanged() metodu ile saat ve telefon tarafında gelen datalar alınabilir. Belirtilen path'e ilgili datalar yazılır. Eğer data uniq değilse bu metoda tekrar girmez. Her Data göndermek istediğimiz de ekstra uniq bir değer ile beraber göndermeliyiz.

<img src="https://github.com/sercanyigit/WearOS-Data-Transfer/blob/main/screenshot/wear1.png" width=25% height=25%>
<img src="https://github.com/sercanyigit/WearOS-Data-Transfer/blob/main/screenshot/mobile1.png" width=25% height=25%>
<img src="https://github.com/sercanyigit/WearOS-Data-Transfer/blob/main/screenshot/mobile2.png" width=25% height=25%>
<img src="https://github.com/sercanyigit/WearOS-Data-Transfer/blob/main/screenshot/wear2.png" width=25% height=25%>
