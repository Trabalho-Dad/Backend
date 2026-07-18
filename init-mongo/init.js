db = db.getSiblingDB("sales_mongo");

db.createCollection("password_reset_codes", {
    validator: {
        $jsonSchema: {
            bsonType: "object",
            required: [
                "email",
                "role",
                "code",
                "used",
                "expiresAt"
            ],
            properties: {
                email: {
                    bsonType: "string"
                },
                role: {
                    bsonType: "string"
                },
                code: {
                    bsonType: "string"
                },
                used: {
                    bsonType: "bool"
                },
                expiresAt: {
                    bsonType: "date"
                }
            }
        }
    }
});

db.password_reset_codes.createIndex(
    { "expiresAt": 1 },
    { expireAfterSeconds: 900 }
);

db.password_reset_codes.createIndex(
    { "email": 1}
)