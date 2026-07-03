db = db.getSiblingDB("sales_mongo");

db.createCollection("password_reset_codes");

db.password_reset_codes.createIndex(
    { "expiresAt": 1 },
    { expireAfterSeconds: 900 }
);

db.password_reset_codes.createIndex(
    { "email": 1}
)