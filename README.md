# EvidencijaStudenti-java
Diplomski specijalistički završni rad - evidentiranje studentske prisutnosti (desktop aplikacija)
<br/>
Za izradu aplikacije korišten je objektno orijentirani programski jezik Java. Aplikacija je razvijena u besplatnom programu „NetBeans IDE“. 
<br/>

<p align="center"><img src="../../blob/master/public/picture/arhitektura.jpg"></p>

<p align="justify">Prilikom evidentiranja studenata preko desktop aplikacije, profesor se prije svega mora prijaviti u sustav sa svojim korisničkim imenom (e-mailom) i lozinkom. Nakon uspješne prijave u sustav profesor testira aplikaciju je li spojena sa čitačem kartica (čitač mora biti priključen u računalo preko USB - Universal Serial Bus porta) i bazom podataka kako bi mogao izabrati kolegij, termin i vrstu predavanje. Profesor može evidentirati studente samo za termin koji je na taj dan ( npr. ako je datum 21.05.2018 profesor može evidentirati studente za taj termin samo toga dana) i u određeno vrijeme (ako termin traje npr. od 15:00 do 17:30, profesor može evidentirati studente samo u tome vremenskom razdoblju). Ukoliko je sva validacija uspješna, studenti se mogu evidentirati. Bilježenje studenata vrši se pomoću studentske kartice (X-ice) na način da svaki student provlači svoju karticu kroz čitač kartica nakon čega se bilježi prisutnost za svakog pojedinog studenta. Profesor može i obrisati studenta s termina prisustva. Osim bilježenja evidencije u profesorskom sučelju imaju još i opcije unosa termina za kolegije profesora, brisanje termina, prikaz studenata po terminu, te pregled studenata po kolegiju.</p><br/>
<p align="justify">Drugi dio desktop aplikacije je administratorsko sučelje koje zahtjeva prijavu administratora u sustav. Administrator ima drugačije privilegije od profesora. On može unositi, izmjenjivati i brisati kolegije, unositi termine za kolegije, unositi nositelja kolegija, unositi studente na kolegij. Osim toga može pregledavati kolegije po profesoru, pregledavati studente po kolegiju i pregledavati studente po određenom terminu kolegija.</p>
