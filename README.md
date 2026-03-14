# 📚 Organizador de Estudos

Um aplicativo **Android nativo** desenvolvido para auxiliar estudantes na organização de suas rotinas e horários de estudo.

Este projeto foi construído integrando os **fundamentos de desenvolvimento mobile** (arquitetura, ciclo de vida e gerenciamento de recursos) com a construção avançada de **interfaces gráficas e experiência do usuário (UX)**.

---

# ✨ Funcionalidades

- **CRUD Completo**  
  Criação, leitura, atualização e exclusão de atividades de estudo.

- **Persistência de Dados**  
  Uso de banco de dados local **SQLite** para salvar as informações de forma permanente no dispositivo.

- **Interface Dinâmica**  
  Listagem fluida das atividades cadastradas utilizando `RecyclerView` e `Adapter` customizado.

- **Suporte Nativo a Dark Mode**  
  Adaptação automática para os temas **Claro** e **Escuro** do sistema, garantindo alto contraste e conforto visual.

- **Validação e Máscaras Inteligentes**  
  Campo de horário com máscara automática em tempo real para o padrão:

HH:mm - HH:mm


- **Foco em UX e Acessibilidade**

  - `AlertDialog` de confirmação antes de excluir registros.
  - Feedback visual não intrusivo utilizando `Snackbar`.
  - Navegação intuitiva com **Up Navigation** (seta de voltar).
  - Componentes acessíveis com `contentDescription`.
  - Textos internacionalizáveis centralizados no `strings.xml`.

---

# 🛠️ Tecnologias Utilizadas

- **Linguagem:** Java  
- **Markup de UI:** XML  
- **Banco de Dados:** SQLite (`SQLiteOpenHelper`)  
- **IDE:** Android Studio  

### Componentes Android

- `ConstraintLayout`
- `RecyclerView`
- `AppCompatEditText`
- `Intents`

### Design

- **Material Design 3**

---

# 📖 Contexto Acadêmico

Este projeto foi desenvolvido como aplicação prática dos **Módulos 1 e 2** da disciplina de **Programação para Dispositivos Móveis**.

## 📱 Módulo 1

Aplicação prática dos conceitos fundamentais da arquitetura Android:

- Manipulação do **ciclo de vida das Activities**
- Atualização automática da lista utilizando `onResume()`
- Compreensão da estrutura do `AndroidManifest.xml`
- Gerenciamento eficiente de dados utilizando **SQLite**

## 🎨 Módulo 2

Construção da interface e experiência do usuário:

- Layouts flexíveis e responsivos utilizando **ConstraintLayout**
- Tratamento de eventos do usuário com **Listeners**
- Navegação entre telas utilizando **Intents**
- Envio de dados entre telas utilizando **Extras**
- Aplicação de boas práticas de design com **Themes** e **Styles** adaptativos

---

# 🚀 Como executar o projeto

## 1️⃣ Clonar o repositório

Abra o terminal e execute:

```bash
git clone https://github.com/fp-torres/ava2progdispmoveis.git
2️⃣ Abrir o projeto
Abra o Android Studio

Clique em File → Open

Navegue até a pasta do projeto que foi clonada

Selecione a pasta raiz do projeto

3️⃣ Sincronizar o Gradle
Após abrir o projeto, aguarde o Gradle Sync finalizar automaticamente.
A barra de progresso aparecerá no canto inferior direito do Android Studio.

4️⃣ Executar o aplicativo
Selecione um emulador Android ou dispositivo físico

Recomendado: Android API 24 ou superior

Clique no botão Run ▶

ou utilize o atalho:

Shift + F10
👨‍💻 Autor
Felipe Torres
Estudante de Análise e Desenvolvimento de Sistemas — UVA
