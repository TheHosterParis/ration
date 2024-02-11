-- Création de la table Animaux
CREATE TABLE Animaux (
    idAnimal INTEGER PRIMARY KEY AUTOINCREMENT,
    nomAnimal TEXT NOT NULL,
    typeAnimal TEXT
);

-- Création de la table Aliment
CREATE TABLE Aliment (
    idAliment INTEGER PRIMARY KEY AUTOINCREMENT,
    nomAliment TEXT NOT NULL,
    description TEXT,
    averageQuantity DOUBLE NOT NULL, -- La quantité moyenne de l'aliment, pour calculer les rations
    unite TEXT, -- par exemple, kg, litre, etc.
    stepQuantity DOUBLE NOT NULL -- Le pas de quantité pour les rations, par exemple 0.5 kg
);

-- Création de la table Ration
CREATE TABLE Ration (
    idRation INTEGER PRIMARY KEY AUTOINCREMENT,
    rationName TEXT,
    dateRation DATE NOT NULL,
    idAnimal INTEGER,
    numberOfAnimals INTEGER NOT NULL, -- Le nombre d'animaux pour lesquels la ration est destinée
    FOREIGN KEY (idAnimal) REFERENCES Animaux(idAnimal)
);

-- Création de la table de jointure RationAliment pour lier Rations et Aliments avec des quantités spécifiques
CREATE TABLE RationAliment (
    idRation INTEGER,
    idAliment INTEGER,
    quantite DOUBLE NOT NULL, -- La quantité de l'aliment dans la ration
    PRIMARY KEY (idRation, idAliment),
    FOREIGN KEY (idRation) REFERENCES Ration(idRation),
    FOREIGN KEY (idAliment) REFERENCES Aliment(idAliment)
);
