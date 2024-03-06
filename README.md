# GestionHopitalVersionJava 
# Le Northern Lights Hospital
Le Northern Lights Hospital (NLH) effectue le suivi des patients qui ont été admis. Le système garde la trace de tous les patients, du médecin de chaque patient, et de la société d’assurances, s’il y a lieu. Le système consigne également le lit qui est occupé par le patient de même que les frais supplémentaires que le patient devra assumer s’il a choisi de louer un téléviseur ou une chambre privée.Lors du développement, l’application n-tier est exécutée sur un seul ordinateur.Au moment de l’implantation de l’application, les tiers seront installés individuellement : les objets de l’interface utilisateur seront exécutés sur des ordinateurs de bureau alors que les objets de l’interface affaires et les bases de données seront exécutées sur un ou sur plusieurs serveurs.
Le système du Northern Lights Hospital fait appel à un système d’ouverture de session simple. Par souci de sécurité :
## Seul l’administrateur de l’hôpital peut ajouter ou supprimer des médecins
## Seuls les préposés aux admissions peuvent ajouter des dossiers d’admissions. Le processus comprend l’ajout de nouveaux patients ou la recherche d’un patient déjà inscrit, l’attribution d’un lit dans une chambre appropriée de même que d’un médecin, l’identification du plus proche parent et de la société d’assurances et l’identification des commodités supplémentaires choisies par le patient ;
## Seuls les médecins peuvent donner un congé aux patients ;
# Règles d’affaires
Voici les règles que le NLH veut observer :
## Il existe trois types de chambres : privée, semi-privée et standard. En général, la chambre standard est sélectionnée ;
## Le NLH ne peut admettre plus de patients que le nombre total de lits disponibles ;
## Les fichiers des patients ne sont jamais supprimés : le lit occupé par le patient est libéré lorsque le patient reçoit son congé de son médecin ;
## Les dossiers d’admission ne sont jamais supprimés ;
## Lorsqu’un patient n’est pas couvert par une assurance privée : s’il n’y a plus de lits disponibles dans une chambre standard alors le préposé aux admissions peut sélectionner, sans aucun frais supplémentaire, la chambre semi-privée de son choix. Le préposé aux admissions peut sélectionner, sans frais supplémentaires, une chambre privée lorsque toutes les chambres semi-privées sont occupées ;
## Le patient qui n’est pas couvert par une assurance privée devra assumer des frais pour une chambre semi-privée ou pour une chambre privée s’il a décidé de prendre ce type de chambre alors qu’une chambre standard était disponible ;
## Les patients qui vont subir une chirurgie sont automatiquement affectés à une chambre du département de chirurgie si un lit correspondant au type choisi est disponible. Dans le cas contraire, l’utilisateur peut sélectionner un autre type de lit ou une autre chambre disponible ;
## Les patients qui sont âgés de 16 ans ou moins qui ne sont pas admis pour une chirurgie sont automatiquement dirigés vers les chambres du département de pédiatrie lorsqu’un lit correspondant au type choisi est disponible. Dans le cas contraire, l’utilisateur peut sélectionner un autre type de lit ou une autre chambre disponible ;
## Voici les tarifs quotidiens : 
### 267$ pour une chambre semi-privée, 
### 571$ pour une chambre privée, 
### 42,50$ pour la location d’un téléviseur et
### 7,50$ pour la location d’un téléphone ;
# Taches
1. Créer la base de données ainsi que les tables correspondantes.
2. Implémenter le système du Northern Lights Hospital sous forme d’une application WPF, en utilisant Entity Framework.
1. Patient (NSS, date naissance, nom, prénom, adresse, ville, province, code postal, téléphone,
IDAssurance*)
2. Admission (IDAdmission, chirurgieprogrammé, dateadmission, datechirurgie, dateducongé, téléviseur, téléphone, NSS*, Numéro Lit*,ID Médecin* )
3. Lit (NuméroLit, occupé, IDType*, IDDépartement*)
4. Type Lit (IDType, description)
5. Département (IDDépartement, nom département)
6. Médecin (IDMédecin, nom, prénom)
7. Assurance (IDAssurance, nomcompagnie)
