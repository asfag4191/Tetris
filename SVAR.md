For å fullføre laben, ber vi deg om å svare på følgende spørsmål. Svar på spørsmålene ved å fylle ut denne filen.

## Hva har du lært om Java og objekt-orientert programmering under arbeidet med denne oppgaven? Beskriv hvilke deloppgave(r) du jobbet med i læringsøyeblikket.

<!-- Jeg har lært mer om arv og hvordan klasser og grensesnitt samhandler, og dette spesielt i steg 1 om arv. Her opprettet jeg en klasse Grid som implementerte IGrid-interfacet. Grid implemterer da metodene i IGrid og det er IGrid som arver (gjennom utvidelse), da "Griddimension" og "Iterable<Gridcell..>". Det er da ikke direkte at Grid arver fra IGrid, men den implementerer dens metoder for å følge IGrid sine mønstre og definisjoner. Det er IGrid som arver "Griddimension" og "Iterable<Gridcell..>" og da utvider dem for å lage mer spesifikke klasser. 

Jeg har lært enda mer om Grensesnitt (Interface), og hvordan dette fungerer. Dette lærte jeg enda mer om på steg 2. Hvor jeg måtte definere metoder i grensesnittet "ViewableTetrisModel". Kunne lage et felles rammeverk slik at klassene Tetrisview og Tetrismodel kunne implementere disse, og å samhandle. Dette viser og til hvordan grensesnitt kan få til å dele funksjonalitet mellom klasser uten å måtte arve. Jeg lærte at flere klasser kunne da implementere den samme oppførselen, og å bruke dem på forskjellig måte. 

Jeg har lært tester og hvor viktige dem er. Jeg fant ut under testen jeg lagte "void testMoveReturnsTrueWhenSuccessful()", og sjekket da når jeg flyttet tetrominoen min ut av brettet, at jeg fikk feil i testen. Så da at det var her problemet lå i koden min og fikk endret dette. Dette var på steg 4, på de siste testene i denne oppgaven. 

På steg 9, lærte jeg blant annet hvordan man kunne implementere bildet fra nett for å lage dette som en bakgrunn på spillet. 
  -->

## Hva er det neste du ønsker å lære om Java og programmering?

<!-- Jeg ønsker å lære hvordan oppnå bedre funksjonalitet i koden, og en ryddigere kode. Få en bedre forståelse rundt objekt orientert programmering. 

Bli trygger på det å iterere gjennom lister, og å vite når det er det er viktig å ha dette med. Var noen tester jeg ikke var helt sikker på når jeg måtte itere gjennom en liste for å sjekke det riktige. 

Jeg ønsker og veldig å lære meg skikkelig hvordan man debugger i Java, slik det blir enklere å se hvor og hva som gjør at koden stopper opp. -->

## Hvilke grep gjør vi for å øke modulariteten i koden? Gi noen eksempeler.

<!-- Jeg følger MVC, Model-View-Controller arkitekturen. Hvor jeg da avgrenser ansvarsområdene mellom Tetrismodel (spilllogikken), Tetrisview(visningen/grafikken) og Tetriscontrol(kontrollen, brukerinteraksjonen). Dette er med på å gjøre endringer i en av disse, uten å endre på dem andre.

Jeg har gitt spillet en konfiguerbarhet, dette vil si at jeg har gjort spillet mer fleksibelt slik som at jeg kan endre på størrelsen på spill brettet, eller fargevalg. Som jeg kan konfigurere i Tetrisview og Tetrismain. 

I tillegg bruker jeg grensesnitt og deler opp koden i mindre selvstendige klasser og metoder. 

Jeg har og laget flere enhetstester som tester f.eks at en tetromino ikke kan gå utenfor brettet, at den dropper der den skal osv. som gjør at jeg sjekker at spillet fungerer slik som forventet underveis. Jeg kan da identifiserer og fikse disse feielne tidlig i progammeringen.  -->
