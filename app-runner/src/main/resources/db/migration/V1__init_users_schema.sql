-- Create extension for UUID generation if needed in raw queries
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE users (
                       id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                       email VARCHAR(255) NOT NULL,
                       full_name VARCHAR(150),
                       avatar_url VARCHAR(500),
                       auth_provider VARCHAR(50) NOT NULL,
                       provider_id VARCHAR(255),
                       timezone VARCHAR(50) NOT NULL DEFAULT 'Asia/Kolkata',
                       is_active BOOLEAN NOT NULL DEFAULT TRUE,
                       created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       version BIGINT NOT NULL DEFAULT 0,
                       CONSTRAINT uk_users_email UNIQUE (email)
);

-- Fast lookup indexes
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_provider ON users(auth_provider, provider_id);