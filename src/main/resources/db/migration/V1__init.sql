CREATE TABLE public.businesses (
	id uuid NOT NULL,
	created_at timestamp(6) NOT NULL,
	name varchar(255) NOT NULL,
	CONSTRAINT businesses_pkey PRIMARY KEY (id),
	CONSTRAINT uk_rtc5owgurf3n1o7phmwbo30sm UNIQUE (name)
);

CREATE TABLE public.accounts (
	id uuid NOT NULL,
	created_at timestamp(6) NOT NULL,
	email varchar(255) NOT NULL,
	"name" varchar(255) NOT NULL,
	"password" varchar(255) NOT NULL,
	"role" varchar(255) NULL,
	verified_email bool NOT NULL,
	business_id uuid NULL,
	CONSTRAINT accounts_pkey PRIMARY KEY (id),
	CONSTRAINT ukaoeaprli6jhgjcliwmdto67bw UNIQUE (email, business_id, role),
	CONSTRAINT fkjnvh20gvoj81geeh3evbtcrim FOREIGN KEY (business_id) REFERENCES public.businesses(id)
);

CREATE TABLE public.verification_tokens (
	id uuid NOT NULL,
	expires_at timestamp(6) NOT NULL,
	account_id uuid NOT NULL,
	CONSTRAINT verification_tokens_pkey PRIMARY KEY (id),
	CONSTRAINT fkq3cp5dsw3df4mt1m62o3ti5o2 FOREIGN KEY (account_id) REFERENCES public.accounts(id)
);