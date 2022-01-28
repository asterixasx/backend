# backend
JAVA Task

Task description:

Reiktų sukurti programos “backend” dalį, kuri leistų užsiregistruoti vartotojui, jam prisijungti, talpinti dienoraščio (blog’o) įrašus, bei juos ištrinti.
Grafinės dalies – nereikia. Bandymams ir veikimo testavimaui galima naudoti POSTman įrankį ar panašią alternatyvą. Pagrindinė užduotis:

Reikalingi vartotojo API:

• prisiregistravimas (email, password) - registruojamės be jokios autorizacijos (GET, PUT, DELETE negalimi)

• prisijungimas (email, password) - autorizacija vyksta per Basic Auth (naudojami tie patys useriai, kurie sukuriami registracijos metu)


Reikalingi dienoraščio API:

• pridėti naują įrašą (title, text); - naują įrašą gali kurti tik registruotas narys

• ištrinti įrašą (trinti pagal ID); - registruotas narys gali trinti tik savo įrašus (administratorius gali trinti bet kokius įrašus)

• gauti vartotojo įrašus; - registruotas narys mato tik savo įrašus (administratorius gali matyti bet kokius įrašus)

• atnaujinti įrašo title/text dalį. - registruotas narys gali atnaujinti tik savo įrašus (administratorius gali redaguoti bet kokius įrašus)


Papildomi balai:

• Panaudojama duombazė (Postgresql, mysql, h2, etc.) - šiuo metu naudojama duombazė PostgreSQL

• Galima lengvai perkelti ant kitos duomenų bazės (jeigu pakeisčiau DB prisijungimą prie kito
tipo duombazės (pvz. Postgresql → MySQL) – ar programa veiktų?) - reiktų pakeisti ne tik prisijungimus, tačiau ir Java dependency, kad galima būtų naudoti kitą duombazę, tačiau tai lengva, logikos ir užklausų keisti nereikia.

• Dienoraščio API apsaugoti ir grąžina tik konkretaus prisijungusio vartotojo dienoraščio
įrašus (pagal sesijas arba naudojant Oauth ar Json Web Token (JWT) ar kokią kitą
autotenfikavimo sprendimą). - Naudojamas BasicAuth sprendimas. Registruotas varotojas gali matyti, keisti, trinti tik savo įrašus.

• Aplikacijos veikimas apsaugotu HTTPS protokolu. - Naudojamas HTTPS protokolas

• Naudojamas Java ORM pasiekti įrašus duomenų bazėje (Hibernate, Jooq, QueryDSL, etc.) - Naudojama Hibernate.


Šiuo metu neįgyvendintos užduotys:

• Visų “endpoint” padengimas unit testais (Spock, JUnit ar kažkuo panašiu). 

• Naudojamas DB versijavimo įrankis (pvz. Liquibase)
