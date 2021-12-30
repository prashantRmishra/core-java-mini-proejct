Customer Table

```sql
CREATE TABLE public.customer
(
    id integer NOT NULL DEFAULT nextval('customer_id_seq'::regclass),
    tradingpartnerid integer,
    tradingpartnername character varying(20) COLLATE pg_catalog."default",
    city character varying(20) COLLATE pg_catalog."default",
    creditlimit integer,
    email character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT customer_pkey PRIMARY KEY (id),
    CONSTRAINT customer_tradingpartnerid_key UNIQUE (tradingpartnerid)
)
```
Supplier Table

```sql
CREATE TABLE public.supplier
(
    id integer NOT NULL DEFAULT nextval('supplier_id_seq'::regclass),
    tradingpartnerid integer,
    tradingpartnername character varying(20) COLLATE pg_catalog."default",
    city character varying(20) COLLATE pg_catalog."default",
    creditbalance integer,
    pannumber character varying(20) COLLATE pg_catalog."default",
    CONSTRAINT supplier_pkey PRIMARY KEY (id),
    CONSTRAINT supplier_tradingpartnerid_key UNIQUE (tradingpartnerid)
)
```