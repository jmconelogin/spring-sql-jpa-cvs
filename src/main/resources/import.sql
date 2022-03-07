INSERT INTO Patient(id, name, email) VALUES (1, 'jaya', 'jaya.kumar@cvshealth.com')
INSERT INTO Patient(id, name, email) VALUES (2, 'marvin', 'marvin.corea@cvshealth.com')
INSERT INTO Patient(id, name, email) VALUES (3, 'mike', 'mike.sherry@cvshealth.com')

-- Your inserts are failing the constratints
--Caused by: org.hsqldb.HsqlException: integrity constraint violation: NOT NULL check constraint; SYS_CT_10092 table: PATIENT column: ID
--theres some setup with the table creation if going to use that annotation, probably easier to hardcode for now

--Can we add some inserts for prescriptions as well please say like 2 or 3 per patient
INSERT INTO Prescription(SCRIPT_ID, DRUG_NAME, PATIENT_NAME, DRUG_ID, QUANTITY, CUSTOMER_INSTRUCTION) VALUES (1, 'pramipexole', 'mike', '10', 250, 'Take until leg stops moving')
INSERT INTO Prescription(SCRIPT_ID, DRUG_NAME, PATIENT_NAME, DRUG_ID, QUANTITY, CUSTOMER_INSTRUCTION) VALUES (2, 'aspirin', 'mike', '12', 500, 'For boss induced headaches, take many as needed')
INSERT INTO Prescription(SCRIPT_ID, DRUG_NAME, PATIENT_NAME, DRUG_ID, QUANTITY, CUSTOMER_INSTRUCTION) VALUES (3, 'penicillin', 'mike', '11', 9, 'For bacterial infections')
INSERT INTO Prescription(SCRIPT_ID, DRUG_NAME, PATIENT_NAME, DRUG_ID, QUANTITY, CUSTOMER_INSTRUCTION) VALUES (4, 'aspirin', 'jaya', '12', 15, 'For headaches')
INSERT INTO Prescription(SCRIPT_ID, DRUG_NAME, PATIENT_NAME, DRUG_ID, QUANTITY, CUSTOMER_INSTRUCTION) VALUES (5, 'wellbutrin', 'jaya', '13', 25, 'To get well')
INSERT INTO Prescription(SCRIPT_ID, DRUG_NAME, PATIENT_NAME, DRUG_ID, QUANTITY, CUSTOMER_INSTRUCTION) VALUES (6, 'penicillin', 'marvin', '11', 1000, 'For bacterial infections')
INSERT INTO Prescription(SCRIPT_ID, DRUG_NAME, PATIENT_NAME, DRUG_ID, QUANTITY, CUSTOMER_INSTRUCTION) VALUES (7, 'codeine', 'marvin', '14', 200, 'Take until pain goes down to 1')
