CREATE OR REPLACE FUNCTION find_latest_otp(
    p_mobile_number VARCHAR
)
RETURNS TABLE (
    id BIGINT,
    mobile_number VARCHAR,
    otp_hash VARCHAR,
    expires_at TIMESTAMP,
    verified BOOLEAN,
    attempt_count INTEGER,
    created_at TIMESTAMP,
    updated_at TIMESTAMP
)
LANGUAGE SQL
AS $$
SELECT
    o.id,
    o.mobile_number,
    o.otp_hash,
    o.expires_at,
    o.verified,
    o.attempt_count,
    o.created_at,
    o.updated_at
FROM otp_master o
WHERE o.mobile_number = p_mobile_number
ORDER BY o.created_at DESC
    LIMIT 1
$$
@@