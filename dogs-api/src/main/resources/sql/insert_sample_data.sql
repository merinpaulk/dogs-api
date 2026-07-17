INSERT INTO dogs
(
    name,
    breed_id,
    supplier_id,
    badge_id,
    gender,
    birth_date,
    date_acquired,
    status_id,
    leaving_date,
    leaving_reason_id,
    kennel_characteristic,
    deleted
)
VALUES

(
'Rex',
1,
1,
'DOG001',
'MALE',
'2020-03-15',
'2021-05-10',
2,
NULL,
NULL,
'Friendly with handlers',
FALSE
),

(
'Luna',
2,
2,
'DOG002',
'FEMALE',
'2021-02-08',
'2022-04-20',
1,
NULL,
NULL,
'Requires quiet kennel',
FALSE
),

(
'Max',
3,
1,
'DOG003',
'MALE',
'2018-07-12',
'2019-08-15',
3,
'2025-01-10',
5,
'Prefers single kennel',
FALSE
),

(
'Bella',
4,
3,
'DOG004',
'FEMALE',
'2019-06-18',
'2020-09-01',
2,
NULL,
NULL,
'Very social',
FALSE
),

(
'Shadow',
6,
4,
'DOG005',
'MALE',
'2017-11-01',
'2018-12-10',
4,
'2024-08-15',
1,
'Needs experienced handler',
FALSE
),

(
'Charlie',
5,
2,
'DOG006',
'MALE',
'2022-01-20',
'2023-03-15',
1,
NULL,
NULL,
'High energy',
FALSE
),

(
'Milo',
1,
4,
'DOG007',
'MALE',
'2020-09-12',
'2021-10-18',
2,
NULL,
NULL,
'Kennel near exercise yard',
FALSE
),

(
'Ruby',
2,
3,
'DOG008',
'FEMALE',
'2019-05-14',
'2020-06-11',
3,
'2024-11-05',
6,
'Requires medication',
FALSE
),

(
'Rocky',
3,
2,
'DOG009',
'MALE',
'2021-08-25',
'2022-11-02',
2,
NULL,
NULL,
'Strong prey drive',
FALSE
),

(
'Daisy',
5,
1,
'DOG010',
'FEMALE',
'2018-04-10',
'2019-06-01',
4,
'2023-12-20',
2,
'Retired and rehomed',
TRUE
);