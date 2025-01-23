INSERT INTO ADMIN (username, email, password) VALUES ('admin', 'admin@admin.com', '12345');

INSERT INTO USER (username, email, password) VALUES ('user', 'user@user.com', '12345');
INSERT INTO USER (username, email, password) VALUES ('user2', 'user2@user2.com', '12345');
INSERT INTO USER (username, email, password) VALUES ('user3', 'user3@user3.com', '12345');

INSERT INTO BOOKS (title, author, genre, description, quantity, available) VALUES ('title1', 'author1', 'genre1', 'description1', 10, true);

-- Romance
INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('Orgulho e Preconceito', 'Jane Austen', 'ROMANCE', 'Um romance clássico que explora questões de classe, casamento e moralidade.', 10, true);

INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('Diário de uma Paixão', 'Nicholas Sparks', 'ROMANCE', 'Uma história de amor emocionante entre Noah e Allie, dois jovens que se apaixonam durante a juventude.', 15, true);

-- Comédia
INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('O Guia do Mochileiro das Galáxias', 'Douglas Adams', 'COMEDIA', 'Uma série de ficção científica cômica cheia de humor absurdo e aventuras intergalácticas.', 8, true);

INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('Boas Omen', 'Neil Gaiman, Terry Pratchett', 'COMEDIA', 'Uma história cômica sobre um anjo e um demônio tentando impedir o apocalipse.', 12, true);

-- Suspense
INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('Os Homens que Não Amavam as Mulheres', 'Stieg Larsson', 'SUSPENSE', 'Um thriller envolvente sobre um jornalista e uma hacker que resolvem um mistério de décadas.', 10, true);

INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('Garota Exemplar', 'Gillian Flynn', 'SUSPENSE', 'Um thriller psicológico que explora as complexas e sombrias relações de um casal casado.', 14, true);

-- Aventura
INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('O Hobbit', 'J.R.R. Tolkien', 'AVENTURA', 'Uma aventura fantástica sobre Bilbo Baggins em sua jornada para recuperar um tesouro guardado por um dragão.', 18, true);

INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('A Ilha do Tesouro', 'Robert Louis Stevenson', 'AVENTURA', 'Uma clássica aventura de piratas que segue Jim Hawkins em sua busca pelo tesouro.', 20, true);

-- Drama
INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('O Sol é Para Todos', 'Harper Lee', 'DRAMA', 'Uma história poderosa sobre raça, justiça e moralidade no sul dos Estados Unidos.', 22, true);

INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('A Culpa é das Estrelas', 'John Green', 'DRAMA', 'Uma comovente história de dois adolescentes lidando com o câncer e o amor que sentem um pelo outro.', 30, true);

-- Terror
INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('It - A Coisa', 'Stephen King', 'TERROR', 'Um romance de terror sobre um grupo de crianças que enfrentam um monstro que muda de forma.', 10, true);

INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('O Iluminado', 'Stephen King', 'TERROR', 'Uma história arrepiante sobre uma família que fica em um hotel assombrado, onde uma força sobrenatural deixa o pai insano.', 8, true);

-- Fantasia
INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', 'FANTASIA', 'O primeiro livro da série Harry Potter, sobre um jovem bruxo que frequenta a Escola de Magia e Bruxaria de Hogwarts.', 50, true);

INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('O Nome do Vento', 'Patrick Rothfuss', 'FANTASIA', 'O primeiro livro da crônica O Chroniqueiro do Rei, seguindo a história de um jovem talentoso chamado Kvothe.', 25, true);

-- Infantil
INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('Harry Potter e a Pedra Filosofal', 'J.K. Rowling', 'INFANTIL', 'O primeiro livro da série Harry Potter, adequado para crianças e leitores mais jovens.', 30, true);

INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('Onde Vivem os Monstros', 'Maurice Sendak', 'INFANTIL', 'Um amado livro infantil sobre um jovem garoto que navega até uma ilha cheia de criaturas selvagens.', 40, true);

-- Autoajuda
INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('O Poder do Agora', 'Eckhart Tolle', 'AUTO_AJUDA', 'Um guia para viver no momento presente e alcançar o crescimento pessoal por meio da atenção plena.', 12, true);

INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('Hábitos Atômicos', 'James Clear', 'AUTO_AJUDA', 'Um livro sobre como formar bons hábitos e quebrar os maus para melhorar sua vida.', 18, true);

-- Informática
INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('Código Limpo', 'Robert C. Martin', 'INFORMATICA', 'Um guia para desenvolvedores de software escreverem códigos limpos, sustentáveis e eficientes.', 10, true);

INSERT INTO BOOKS (title, author, genre, description, quantity, available)
VALUES ('O Programador Pragmático', 'Andrew Hunt, David Thomas', 'INFORMATICA', 'Um clássico atemporal sobre os princípios essenciais e práticas para desenvolvedores de software.', 15, true);
