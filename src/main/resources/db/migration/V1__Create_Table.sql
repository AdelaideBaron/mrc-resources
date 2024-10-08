CREATE TABLE boats
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    name                VARCHAR(100) NOT NULL,
    avg_crew_weight     FLOAT(2),
    boat_type           ENUM('SINGLE_SCULL', 'DOUBLE_SCULL', 'COXLESS_QUAD', 'COXED_QUAD','OCTUPLE', 'COXLESS_PAIR', 'COXLESS_FOUR', 'COXED_PAIR', 'COXED_FOUR', 'COXED_EIGHT'),
    minimum_rower_level ENUM('DEVELOPMENT', 'NOVICE', 'INTERMEDIATE', 'SENIOR') DEFAULT 'INTERMEDIATE',
    status              ENUM('WORKING', 'WORKING_WITH_FAULTS', 'DO_NOT_USE_FAULTY', 'DO_NOT_USE_OTHER') DEFAULT 'WORKING',
    best_blades_id      INT
);

CREATE TABLE blades
(
    id     INT AUTO_INCREMENT PRIMARY KEY,
    name   VARCHAR(100) NOT NULL,
    amount INT,
    status ENUM('WORKING', 'WORKING WITH FAULTS','DO NOT USE - FAULTY', 'DO NOT USE - OTHER') DEFAULT 'WORKING'
);

CREATE TABLE resources_in_use
(
    id                  INT AUTO_INCREMENT PRIMARY KEY,
    resource_id         INT NOT NULL,
    type                ENUM('BOAT', 'BLADE') NOT NULL,
    quantity            INT DEFAULT 1,
    upcoming_session_id INT,
    date                DATE NOT NULL,
    start_time          TIME NOT NULL,
    end_time            TIME NOT NULL
)