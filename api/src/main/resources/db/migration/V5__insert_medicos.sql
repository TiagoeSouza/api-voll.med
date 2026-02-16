DELETE
FROM MEDICOS;

INSERT INTO medicos
(nome, email, crm, especialidade, logradouro, bairro, cep, complemento, numero, uf, cidade, telefone)
VALUES ('Tiago Esteves de Souza', 'tiago.souza@med.voll.com', '012345', 'GERAL', 'Rua Dos Bobos', 'Centro',
        '01009-000', 'CASA', '0', 'SP', 'São Paulo', '(11) 98765-4321'),

       ('Carlos Henrique Almeida', 'carlos.almeida@clinicavida.com', '123456', 'CARDIOLOGIA', 'Rua das Flores',
        'Centro', '01001-000', 'Sala 101', '123', 'SP', 'São Paulo', '(11) 98765-4321'),

       ('Mariana Souza Lima', 'mariana.lima@clinicavida.com', '234567', 'DERMATOLOGIA', 'Avenida Paulista',
        'Bela Vista', '01311-200', 'Conjunto 45', '1578', 'SP', 'São Paulo', '(11) 97654-3210'),

       ('Roberto Silva Santos', 'roberto.santos@clinicavida.com', '345678', 'ORTOPEDIA', 'Rua XV de Novembro', 'Centro',
        '80020-310', NULL, '456', 'PR', 'Curitiba', '(41) 99876-1234'),

       ('Fernanda Costa Oliveira', 'fernanda.oliveira@clinicavida.com', '456789', 'GINECOLOGIA', 'Rua Bahia', 'Savassi',
        '30160-011', 'Sala 302', '789', 'MG', 'Belo Horizonte', '(31) 98877-6655'),

       ('Eduardo Pereira Gomes', 'eduardo.gomes@clinicavida.com', '567890', 'CARDIOLOGIA', 'Avenida Atlântica',
        'Copacabana', '22021-001', 'Apto 1201', '1702', 'RJ', 'Rio de Janeiro', '(21) 97766-5544'),

       ('Juliana Martins Rocha', 'juliana.rocha@clinicavida.com', '678901', 'GIMysql.10#NECOLOGIA',
        'Rua Sete de Setembro',
        'Centro', '40060-001', NULL, '321', 'BA', 'Salvador', '(71) 99665-4433'),

       ('Ricardo Andrade Lopes', 'ricardo.lopes@clinicavida.com', '789012', 'ORTOPEDIA', 'Avenida Beira Mar',
        'Meireles', '60165-121', 'Sala 210', '890', 'CE', 'Fortaleza', '(85) 98888-1122'),

       ('Patrícia Mendes Ferreira', 'patricia.ferreira@clinicavida.com', '890123', 'DERMATOLOGIA', 'Rua Padre Chagas',
        'Moinhos de Vento', '90570-080', NULL, '150', 'RS', 'Porto Alegre', '(51) 99777-3344'),

       ('Lucas Barbosa Ribeiro', 'lucas.ribeiro@clinicavida.com', '901234', 'ORTOPEDIA', 'Avenida Afonso Pena',
        'Centro', '79002-072', 'Bloco B', '456', 'MS', 'Campo Grande', '(67) 99666-7788'),

       ('Camila Teixeira Duarte', 'camila.duarte@clinicavida.com', '112233', 'CARDIOLOGIA', 'Rua das Palmeiras',
        'Jardim América', '74000-000', NULL, '789', 'GO', 'Goiânia', '(62) 99887-4455'),

       ('Thiago Moreira Alves', 'thiago.alves@clinicavida.com', '223344', 'ORTOPEDIA', 'Avenida Getúlio Vargas',
        'Funcionários', '30112-020', 'Sala 404', '1000', 'MG', 'Belo Horizonte', '(31) 99775-8899'),

       ('Aline Rodrigues Pinto', 'aline.pinto@clinicavida.com', '334455', 'DERMATOLOGIA', 'Rua Dom Pedro II', 'Centro',
        '69005-010', NULL, '210', 'AM', 'Manaus', '(92) 99999-0001'),

       ('Bruno Carvalho Nunes', 'bruno.nunes@clinicavida.com', '445566', 'CARDIOLOGIA', 'Avenida Norte Sul', 'Cambuí',
        '13025-320', 'Sala 12', '850', 'SP', 'Campinas', '(19) 98888-7766'),

       ('Larissa Figueiredo Melo', 'larissa.melo@clinicavida.com', '556677', 'GINECOLOGIA', 'Rua Amazonas', 'Centro',
        '69900-000', NULL, '95', 'AC', 'Rio Branco', '(68) 99777-2211'),

       ('Felipe Cardoso Araújo', 'felipe.araujo@clinicavida.com', '667788', 'CARDIOLOGIA', 'Avenida Tancredo Neves',
        'Caminho das Árvores', '41820-021', 'Sala 808', '3000', 'BA', 'Salvador', '(71) 98811-2233');