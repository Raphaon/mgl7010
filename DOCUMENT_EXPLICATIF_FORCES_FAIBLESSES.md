# Document explicatif : forces et faiblesses du projet

## 1) Contexte
Ce projet implémente un modèle de gestion académique (personnes étudiantes, programmes, cours, inscriptions, notes et diplomation) en Java, avec une séparation en deux grands espaces :
- `types` : contrats (interfaces, énumérations, records, exceptions);
- `implementations` : classes concrètes.

Cette approche facilite la compréhension du domaine tout en permettant de valider le comportement via des tests.

## 2) Forces du projet

### 2.1 Architecture claire et orientée interfaces
- Le code est structuré autour d’interfaces métier explicites (`PersonneEtudiante`, `Programme`, `InscriptionProgramme`, etc.), ce qui favorise l’extensibilité et la testabilité.
- Les classes d’implémentation restent concentrées sur la logique métier attendue.

### 2.2 Cohérence du modèle domaine
- Les objets principaux reflètent bien des concepts universitaires réels : inscription à un programme, inscription à des cours, attribution de notes, validation des préalables et diplomation.
- Le flux métier est naturel : `PersonneEtudiantImpl` délègue les calculs académiques à `InscriptionProgrammeImpl`.

### 2.3 Robustesse de base face aux cas nuls
- Plusieurs méthodes défensives évitent des erreurs à l’exécution (vérifications de `null` dans l’inscription, l’annulation, et l’attribution de notes).
- Les collections sont initialisées dès la construction, réduisant le risque de `NullPointerException`.

### 2.4 Logique de diplomation lisible
- Les critères de diplomation sont centralisés : cours obligatoires complétés, crédits suffisants, seuil de moyenne cumulative atteint.
- Cette logique est explicite et facile à maintenir.

### 2.5 Utilisation adéquate des API standard
- Le projet utilise des types Java standards (`Instant`, `List`, `Map`, `Iterator`) sans dépendance externe.
- Cela simplifie la portabilité et l’exécution dans des environnements variés.

## 3) Faiblesses et limites

### 3.1 Validation des entrées incomplète
- Certaines constructions (ex. création de personne, programme, cours) n’imposent pas systématiquement de validation stricte des chaînes vides, formats, bornes, etc.
- À long terme, cela peut laisser entrer des données incohérentes dans le modèle.

### 3.2 Gestion des doublons perfectible
- L’inscription à un cours est indexée par sigle, ce qui simplifie l’accès, mais écrase potentiellement une inscription existante au même sigle (selon les sessions/retakes).
- Le modèle actuel ne distingue pas explicitement plusieurs tentatives d’un même cours dans des sessions différentes.

### 3.3 Moyenne cumulative potentiellement simplifiée
- Le calcul actuel de moyenne cumulative est une moyenne simple des notes numériques des cours du programme.
- Dans plusieurs contextes académiques, la moyenne cumulative est pondérée par le nombre de crédits; cette nuance peut être une limite selon les exigences réelles.

### 3.4 Couverture fonctionnelle orientée « minimum requis »
- Le projet semble implémenter prioritairement les comportements nécessaires aux tests fournis.
- Des règles métier plus avancées (préalables de cours détaillés, reprise de cours avec remplacement de note, contraintes de session) ne sont pas formalisées.

### 3.5 Documentation technique limitée
- Le dépôt ne contient pas de guide détaillé d’exécution (commande unique de build/test), ni de documentation d’architecture plus poussée.
- Pour un nouveau contributeur, l’onboarding peut demander une lecture manuelle des interfaces et tests.

## 4) Recommandations d’amélioration

1. **Renforcer les validations**
   - Valider les champs critiques à la construction (valeurs nulles/vides, bornes numériques, formats attendus).

2. **Gérer explicitement les reprises de cours**
   - Utiliser une clé composite (sigle + session) ou une structure permettant plusieurs inscriptions au même sigle.

3. **Préciser la règle de moyenne cumulative**
   - Confirmer la règle métier (pondérée ou non) et aligner l’implémentation/documentation.

4. **Étendre les tests**
   - Ajouter des tests de robustesse : entrées invalides, doubles inscriptions, reprise de cours, scénarios limites de diplomation.

5. **Améliorer la documentation du projet**
   - Ajouter un README opérationnel (pré-requis, compilation, exécution des tests, conventions).

## 5) Conclusion
Le projet présente une **base saine, lisible et cohérente** pour modéliser un parcours académique. Ses principales forces résident dans la séparation interface/implémentation et dans une logique métier simple à suivre. Les principales faiblesses concernent surtout la **profondeur des validations** et certaines **règles académiques avancées** qui restent à formaliser.

En bref : une bonne fondation pour un TP ou un prototype, avec un bon potentiel d’évolution vers une solution plus complète.
